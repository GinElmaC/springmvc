package com.GinElmaC.springmvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class forwardController {
    @RequestMapping("/a")
    public String toA(){
//        return "a";
        return "forward:b";
    }
    @RequestMapping("/b")
    public String toB(){
        return "b";
    }
}
