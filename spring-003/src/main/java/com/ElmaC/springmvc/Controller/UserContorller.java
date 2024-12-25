package com.ElmaC.springmvc.Controller;

import com.ElmaC.springmvc.Pojo.UserPojo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class UserContorller {
    @RequestMapping("/")
    public String toRegister(){
        return "register";
    }
//    第一种方法
//    @PostMapping("/user/regiest")
//    public String regiest(HttpServletRequest request){
//        //HttpServletResponse,HttpServletResponse,HttpSession都属于原生Servlet API，springmvc会自动帮我们创建并传入
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String sex = request.getParameter("sex");
//        String[] interest = request.getParameterValues("interest");
//        String intro = request.getParameter("intro");
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(sex);
//        System.out.println(interest.toString());
//        System.out.println(intro);
//        return "ok";
//    }

//    第二种方法
//    @PostMapping("/user/regiest")
//    public String regiest(
//            @RequestParam(value = "username")
//            String username,
//            @RequestParam("password")
//            String password,
//            @RequestParam("sex")
//            Integer sex,
//            @RequestParam("interest")
//            String[] interest,
//            @RequestParam("intro")
//            String intro){
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(sex);
//        System.out.println(Arrays.toString(interest));
//        System.out.println(intro);
//        return "ok";
//    }

//    第三种方法
    @PostMapping("/user/regiest")
    public String regiest(UserPojo userPojo,HttpServletRequest httpServletRequest){

    System.out.println(userPojo);
    return "ok";
    }
}
