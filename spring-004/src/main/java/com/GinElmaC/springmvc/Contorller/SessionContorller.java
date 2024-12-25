package com.GinElmaC.springmvc.Contorller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//这个注解里面的值可以有很多个，表示这个值是想Session中存放的，而不是request
@SessionAttributes(value={"testSession"})
public class SessionContorller {
    @RequestMapping("/testSessionServletAPI")
    public String testServletAPI(HttpSession httpSession){
        //处理核心业务
        //将数据存储到session中
        httpSession.setAttribute("testSession","利用原生ServletAPI在session中存放的数据");
        //返回逻辑视图名称（这是一次转发行为）
        return "ok";
    }
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(ModelMap map){
        map.addAttribute("testSession","利用SessionAttributes注解存放的数据");
        return "ok";
    }
}
