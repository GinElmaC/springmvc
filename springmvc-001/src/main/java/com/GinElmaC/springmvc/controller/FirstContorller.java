package com.GinElmaC.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstContorller {

    @RequestMapping("/test")
    public String hehe(){
        return "first";
    }
    @RequestMapping("haha")
    public String haha(){return "other";}
    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
