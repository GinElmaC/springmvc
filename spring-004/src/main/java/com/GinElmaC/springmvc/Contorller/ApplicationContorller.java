package com.GinElmaC.springmvc.Contorller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;

@Controller
public class ApplicationContorller {
    @RequestMapping("/testApplicationServletAPI")
    public String testServletAPI(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("testApplication","利用ServletAPI存放的数据");
        return "ok";
    }
}
