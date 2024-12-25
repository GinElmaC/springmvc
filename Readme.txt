springmvc框架课堂笔记
1.第一个springmvc程序开发流程
    1.1开发流程：
        1.创建一个空的工程。
        2.设置JDK版本。
        3.设置Maven。
        4.创建Maven模块。
        5.设置打包方式war
        6.引入依赖
            springmvc
            servlet
            thymelead spring6
            log4j
    1.2给maven模块添加web支持
        1.在模块下新建webapp目录（带小蓝点）
        2.在webapp/WEB-INF目录下添加web.xml（注意文件路径）
    1.3在web.xml文件中配置：前端控制器（springmvc框架内置的一个类：DispatcherServlet），所有的请求都应该经过这个DispatcherServlet的处理。
        重点：<url-pattern>/<url-pattern>
        这里的/表示除了以.jsp结尾的请求路径外所有的请求路径都走DispatcherServlet，因为jsp底层其实就是一个servlet，在请求jsp时应该走他自己的servlet。
        如果改为/*则表示包括.jsp在内的所有请求路径。
    1.4编写Controller，在加入Srping注解Controller，将类的创建交给spring容器，纳入Ioc管理。
    1.5配置springmvc自己的配置文件
        这个配置文件有默认名字：<servlet-name>-servlet.xml
        这个配置文件有默认的存放位置：WEB-INF
    1.6在配置文件里添加扫描组件（扫描写着Contorller的包）
    1.7在配置文件中添加视图解析器
    1.8根据视图解析器内容：
        <!--设置模板文件的位置（前缀）-->
        <property name="prefix" value="/WEB-INF/templates/"/>
        <!--设置模板文件后缀（后缀），Thymeleaf文件扩展名不一定是html，也可以是其他，例如txt，大部分都是html-->
        <property name="suffix" value=".thymeleaf"/>
        <!--设置模板类型，例如：HTML,TEXT,JAVASCRIPT,CSS等-->
        <property name="templateMode" value="HTML"/>
        创建相应的目录以及文件，这里要在WEB—INF下创建名字为templates的目录
        并在目录中创建后缀为thymeleaf的文件
        并在创建的文件中写html类型的代码。
    1.9写contorller代码
        在contorller中添加实例方法，方法返回String，并加上@RequestMapping(value = "xxx")注解。
        重点：当我们在浏览器输入http:...../xxx时，这个请求首先会经过DispatcherServlet，DispatcherServlet会获取请求中的/xxx
        然后会在扫描的包中找到@RequestMapping注解中value与/xxx相同的方法并获取返回的String作为逻辑视图名称
        然后springmvc会在逻辑视图名称前后添加东西，形成/WEB-INF/templates/xxx.thymeleaf的物理视图名称
        然后会在你创建的templates的目录下查找这个物理视图名称并按照配置文件中的html解析。
    1.99配置tomcat服务器
2.springmvc配置文件名字和存放位置是可以指定的
    通过web.xml配置文件指定
        在DispatcherServlet的servlet标签中加入：
            <!--通过selvlet的初始化参数指定sptingmvc配置文件的名字和位置-->
            <init-param>
                <param-name>contextConfigLocation</param-name>
                <!--
                指定了springmvc配置文件名字叫springmvc.xml
                指定了springmvc配置文件的存放路径是：类的根路径(resources)
                -->
                <param-value>classpath:springmvc.xml</param-value>
            </init-param>
    建议：另外再加入
        <load-on-startup>0</load-on-startup>
        这个会在服务器启动的时候初始化DispatcherServlet对象，可以加快第一次访问速度
        如果不加，默认是在第一次请求的时候初始化对象
3.@RequestMapping可以出现在类上，也可以出现在方法上
    @RequestMapping的源码中有@Target({ElementType.TYPE, ElementType.METHOD})
    意思是这个注解可以出现在类上和方法上
    @Controller
    @RequestMapping("/a")
    public class FirstContorller {
        @RequestMapping("/b")
        public String index(){
            return "index";
        }
    }
    意思是当访问http:...../a/b时会跳转/b的页面
4.@RequestMapping的value可以有多个
    @RequestMapping的源码中value的返回值是Stirng数组：
    @AliasFor("value")
    String[] path() default {};
    如果value的值有多个，代表不管是访问第一个还是访问第二个，都可以跳转修饰的方法上
    value也可以用path代替，二者源码相同
    @AliasFor("path")
    String[] value() default {};
5.@RequestMapping的value支持Ant风格（模糊匹配）
    ？表示任意一个字符，注意，不可以空着，也不可以是？或者/
    *表示0到N个任意字符，注意，可以空着，不可以是?或者/
    **表示0到N个任意字符，可以是/，注意，不可以是？，如果是/，那么**两边必须是/
6.@RequestMapping的value上的占位符（重点）
    传统的URL：
        /springmvc/login?username=admin&password=123&email=admin

    现在开发比较流行RESTFul风格的url:
        /springmvc/login/admin/123

    那么怎么从这种风格的url中取出数据呢：
        这里就是RESTFUL风格的url
        @RequestMapping("/login/{username111}/{password111}")
        public String testRestFul(
                @PathVariable("username111") String username,
                @PathVariable("password111") String password){
            System.out.println("用户名："+username+" "+"密码:"+password);
            return "index";
        }
        在@RequestMapping的注解中使用{}占位符，并传入名字
        在下方方法的传入参数中使用@PathVariable注解，并在注解中放入占位符中的名字即可将url中的数据取出
7.@RequestMapping的method属性：
    指定前端在进行访问时所使用的提交方式
    @RequestMapping(value = "/user/login",method = {RequestMethod.POST})
    public String testMethod(){
        System.out.println("logining...-----------");
        return "index";
    }
    如果前端请求了/user/login并且提交方式是post时，才能进行下方的操作，其中任何一个不满足，则无法进行下方操作。
    405报错就是get与post的问题
8.衍生Mapping
    @PostMapping 代替的是@RequestMapping(value={},method={RequsetMethod.POST})
    @GetMapping 代替的是@RequestMapping(value={},method={RequsetMethod.GET})
    .......
9.@RequestMapping的params属性
    params会限制前端请求的参数：
        @RequestMapping(value="/login", params={"username", "password"})
        表示请求必须包括username和password，例如/login?username=aaa&password=123是可以的，/login?username=aaa不可以
        @RequestMapping(value="/login", params={"!username", "password"})
        表示请求必须不包含username，但必须包括password，例如/login?password=123
        @RequestMapping(value="/login", params={"username=admin", "password"})
        表示请求必须包含username和password并且username必须是admin
        @RequestMapping(value="/login", params={"username!=admin", "password"})
        表示请求必须包含username和password并且username不可以等于admin
10.@RequestMapping的header属性
    header会限制请求的请求头信息，与params用法相同，只是限制的东西不一样
11.获取请求提交的数据：400错误就是后端没有获得前端提供的数据
    11.1使用原生的Servlet API
        在处理器的方法参数上提供HttpServletRequest
        springmvc框架会自动将tomcat服务器创建的request对象传递给处理器方法
        我们直接在处理器方法中使用request对象即可
        getParameter(String)方法中传入的String是form表单中input的name
        当然，HttpServletResponse，HttpSession如果有需要的话，也是直接传入即可
        缺点：依赖于原生的Servlet，无法做单元测试，一般不用
    11.2使用springmvc提供的注解@RequestParam(请求参数)
        @RequestParam(value = "username") value/name 的值必须是form表单的name
        String aaa 方法的参数名字没有限制
        这个注解也可以进行自动类型转换
        @RequestParam("sex")
        Integer sex
        传入的sex是"1"，springmvc会将他自动转化为Integer类型
        12.2.1@RequestParam注解的require属性
            value默认的require是true，即必须得，当前端没有传入这个参数时，会报400错误
            如果设置为false，则不是必须的，不会报400错误，但输出是会输出null
        12.2.2@RequestParam注解的defaultValue属性
            这个属性是和require属性结合使用的当前端没有传入这个属性的值是，会使用设置的默认值填充
    11.3使用控制器方法上的形参名来接收
        @RequestParam(value = "username")
        String username
        这种情况下上方的注解可以不写，但前提是在pom.xml里添加以下东西：
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.12.1</version>
                    <configuration>
                        <source>21</source>
                        <target>21</target>
                        <compilerArgs>
                            <arg>-parameters</arg>
                        </compilerArgs>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    11.4使用Pojo类或javaBean类接收数据（这是最常用的）
        底层的原理就是通过反射机制。
        使用这种方式的前提是Pojo类的这个属性的set方法名取出set后必须和请求参数的参数名保持一致
        什么意思？就是假如传入的参数名时username，但Pojo类的属性名为a，那么下面的set方法也是对的：
            public void setusername(String a){
                this.a = a;
            }
        因为底层原理是通过反射机制获取到Pojo类的class码文件，然后通过form表单传入的参数名拼出set方法，然后调用Pojo类中的拼出的set方法，如果方法不存在，则不会赋值，输出的会是null
        我们只需要在控制器方法的形参中传入Pojo对象即可
        @PostMapping("/user/regiest")
        public String regiest(UserPojo userPojo){
        System.out.println(userPojo);
        return "ok";
        }
12.获取请求头的信息
    使用@RequestHeader注解映射到控制器方法的形参上
13.获取请求的Cookie数据
    使用@CookieValue注解映射到控制器方法的形参上
14.从request域中获取数据
    14.1利用原生ServletAPI
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("testServletAPI","测试request的存");
        return "ok";
    }
    14.2利用Model接口
    springmvc会把model对象自动填充进来
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testServletAPI","利用model接口存入的数据");
        return "ok";
    }
    14.3利用Map方式
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("testServletAPI","利用Map接口存入的数据");
        return "ok";
    }
    14.3利用ModelMap方式
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap map){
        map.addAttribute("testServletAPI","利用modelmap类存入的数据");
        return "ok";
    }
    关于model方法、map方法、modelmap方法的关系：
        表面上这是三个不同的方式，但底层这三个使用的都是同一个对象
        BindingAwareModelMap继承了ExtendedModelMap类
        ExtendedModelMap继承了ModelMap类
        ExtendedModelMap实现了Model接口
        ModelMap类继承了LinkedHashMap
    14.4利用ModelAndView方法
    @RequestMapping("/testModelandView")
    public ModelAndView testModelandView(){
        //创建ModelandView方法
        ModelAndView mav = new ModelAndView();
        //添加绑定数据
        mav.addObject("testServletAPI","利用modelandview存入的数据");
        //绑定视图
        mav.setViewName("ok");
        //返回ModelandView对象
        return mav;
    }

    以上除了原生ServletAPI方法外的所有方法其实底层最终都会返回一个ModelAndView对象，就算返回的是String，
    springmvc在拼物理视图的时候也会返回一个ModelAndView对象给DispatcherServlet类。

    当请求路径不是JSP的时候，都会走前端控制器DispatcherServlet。
    DispatcherServlet中有一个核心方法doDispatch()，这个方法用来通过请求路径找到对应的处理器方法
    然后调用处理器方法，处理器方法返回一个逻辑视图名称（也可能直接返回一个ModelAndView对象），底层
    会将逻辑视图名称转换为View对象，然后将View对象结合Model对象，封装成一个ModelAndView对象，然后将
    该对象返回给DispatcherServlet。
15.获取session域内的数据
    15.1使用原生ServletAPI
    @RequestMapping("/testSessionServletAPI")
    public String testServletAPI(HttpSession httpSession){
        //处理核心业务
        //将数据存储到session中
        httpSession.setAttribute("testSession","利用原生ServletAPI在session中存放的数据");
        //返回逻辑视图名称（这是一次转发行为）
        return "ok";
    }
    15.2使用SessionAttributes注解
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(ModelMap map){
        map.addAttribute("testSession","利用SessionAttributes注解存放的数据");
        return "ok";
    }
    同时在这个控制器类前加上@SessionAttributes(value={"testSession"})注解
    表示testSession存放在Session中而不是Request中
16.获取Applcation域中存放的数据
    16.1利用原生ServletAPI
    ServletContext对象不可直接写在方法形参中，应该利用request或session间接获取
    @RequestMapping("/testApplicationServletAPI")
    public String testServletAPI(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("testApplication","利用ServletAPI存放的数据");
        return "ok";
    }
17.springmvc中重要的视图
    InternalResourceView：内部资源视图（是springmvc内置的，专门负责解析JSP末班语法的，另外也负责转发forward功能的实现，因为转发也是内部资源的跳转）
    RedirectView：重定向视图（是springmvc内置的，专门负责重定向redirect功能实现的）
    ThymeleafView：thymeleaf视图（是第三方的，专门负责解析thymeleaf模版语法的）
18.实现视图机制的核心类和核心接口
    1.DispatcherServlet：前端控制器
        负责接收前端的请求（/）
        根据请求路径找到对应的处理器方法
        执行处理器方法
        返回ModelAndView对象
        再往下就是处理视图
    2.ViewResolver接口：视图解析器接口（ThymeleafViewResolver实现了这个接口，InternalResourceViewResolver也是实现了这个接口）
        这个接口干什么事？
            根据配置文件中配置的前缀后缀将逻辑视图名称转化为物理视图名称
            并最终返回一个View就扣对象
        核心方法是什么？
            View resolveViewName(String viewName, Locale locale) throws Exception;
    3.View接口：视图接口（thymeleafView实现了这个接口，InternalResourceView实现了这个接口）
        这个接口干什么事？
            负责将模版语法的字符串转换成html代码，并且将html代码响应给浏览器。（渲染）
        核心方法是什么？
            void render(@Nullable Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
19.在springmvc中是怎么通过代码完成转发和重定向的
    @RequestMapping("/a")
    public String toA(){
        return "a";
    }
    注意：当return "a";的时候，返回了一个逻辑视图名称。这种方式跳转到视图，默认采用的就是forward方式跳转过去的。只不过这个底层创建的视图对象：ThymeleafView
    @RequestMapping("/a")
    public String toA(){
        //转发到/b
        return "forward:/b";
    }
    这样就是实现了转发，注意：forward后面的不能随便写，应该是要转发到的请求复制过来。
    这是一共是一次请求，底层创建的是InternalResourceView对象
    @RequestMapping("/a")
    public String toA(){
        //重定向到/b
        return "redirect:/b";
    }
    这样就是实现了重定向，一共是两次请求，底层创建的是RedirectView对象

    总结：
        转发：return "forward:/b"底层创建的是InternalResourceView对象
        重定向：return "redirect:/b"底层创建的是RedirectView对象
        如果重定向要实现跨域，则要这样写:return "redirect:http://localhost:8080/springmvc/b"，要写上全名。
20.<mvc:view-controller>，<mvc:annotation-driven/>
    这个配置信息可以再springmvc中进行配置，作用是什么？
        如果一个Controller的方法只是为了完成视图跳转，没有任何业务，那么这个控制器可以不写
        直接在springmvc配置文件中添加
            <mvc:view-controller path="/test" view-name="test"/>
        这个注解会在收到/test的请求后，将后面的view-name当做逻辑视图来获取物理视图并进行跳转。
        但这个注解会使整个项目中所有注解失效，需要另外配置开启注解：
            <mvc:annotation-driven/>
        这个注解会重新开启注解驱动
21.静态资源处理
    静态资源就是指css,js,图片等资源
    假设我们在webapp目录下有static/img/bizhi.jpg
    我们不可以直接springmvc/static/img/bizhi.jpg获得资源，因为DispatcherServlet没有处理静态资源的能力
    1.默认的Servlet方法
        我们需要开启默认的Servlet
        服务器内部都会有一个servlet类用来处理静态资源
        tomcat内部有DefaultServlet类可以处理静态资源
        需要再springmvc配置文件中添加
            <!--开启默认的Servlet处理-->
            <mvc:default-servlet-handler/>
        开启之后，当我们访问springmvc/static/img/bizhi.jpg的时候，先走DispatcherServlet，如果出现了404错误，就会走默认Servlet找资源。、
    2.配置静态资源处理器
        <!--配置静态资源处理器-->
        <mvc:resources mapping="/static/**" location="/static/"/>
        这个标签的意思是，当访问/static/....的资源时，都去/static/这个目录下找
22.什么是RESTful？
    RESTful是对WEB服务接口的一种设计风格，提供了一套约束，可以让WEB服务接口更加简洁、易于理解。
    REST对请求方式的约束是这样的：
        查询必须用get
        新增必须用post
        修改必须用put
        删除必须用delete
    RESTful对url的约束是这样的：
        GET/user/1 查1个
        GET/user 查所有
        POST/user 新增
        PUT/user 修改
        DELETE/user/1 删除
    RESTful:表述性状态转移
23.RESTful风格在修改的时候如何发送PUT请求
    第一步：要想发送PUT请求，首先必须是一个POST请求。（POST请求是大前提，因为修改会改变数据库数据）
    第二步：在post请求的表单中添加隐藏域，隐藏域必须这么写，type和name都不能变，一定是这个值
        <!--隐藏域-->
        <input type="hidden" name="_method" value="PUT">
    第三步：在web.xml文件中添加一个过滤器
        <!--添加一个过滤器，这个过滤器是springmvc提前写好的，直接用就行了，这个过滤器可以帮我们把post请求转化为put/delete请求-->
        <filter>
            <filter-name>hiddenfilter</filter-name>
            <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
        </filter>
        <filter-mapping>
            <filter-name>hiddenfilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
        注意：如果想解决乱码问题，这个过滤器一定要写在字符编码过滤器后面
        因为我们都知道，字符编码过滤器要在request.getParameter()方法获取参数前进行，
        而我们添加的过滤器底层要获取你后面写的PUT/DELETE来转化，他底层有一个request.getParameter()方法，所以要放在后面
24.HttpMessageConverter消息转换器
    转换器转换的是什么？
        转换的是Http协议与java程序中的对象之间的互相转换
        比如，将请求体中的数据自动转换为java对象时就用到了FormHttpMessageConverter转换器
25.在springmvc中如何使用原生的ServletAPI完成AJAX请求
    @GetMapping("/ajax")
    public void ajax(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.Print("hello ajax");
    }
    @GetMapping("/ajax")
    public String ajax(HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.print("hello ajax");
    }
26.@ResponseBody注解（非常重要，使用非常多，因为以后大部分都是ajax请求）
    @GetMapping("/ajax")
    @ResponseBody
    public String ajax() throws IOException{
        return "hello ajax";
    }
    重点：一旦处理器方法上添加了@ResponseBody注解，那么return返回的不再是一个逻辑视图名称，而是直接将返回的结果采用字符串的方式响应给浏览器。
    使用的消息转换器是：StringHttpMessageConverter
    底层原理实际上代替的就是：
        PrintWriter out = response.getWriter();
        out.print("hello ajax");