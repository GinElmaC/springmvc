package com.GinElmaC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FirstContorller {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    //这里就是RESTFUL风格的url
    @RequestMapping("/login/{username}/{password}")
    public String testRestFul(
            @PathVariable("username") String username,
            @PathVariable("password") String password){
        System.out.println("用户名："+username+" "+"密码:"+password);
        return "index";
    }
    @RequestMapping(value = "/user/login",method = {RequestMethod.POST})
    public String testMethod(){
        System.out.println("logining...-----------");
        return "index";
    }
}
