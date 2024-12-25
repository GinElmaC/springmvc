package com.GinElmaC.springmvc.Contorller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RequestContorller {
    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("testServletAPI","测试request的存");
        return "ok";
    }
    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("testServletAPI","利用model接口存入的数据");
        System.out.println(model.getClass().getName());
        return "ok";
    }
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("testServletAPI","利用Map接口存入的数据");
        System.out.println(map.getClass().getName());
        return "ok";
    }
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap map){
        map.addAttribute("testServletAPI","利用modelmap类存入的数据");
        System.out.println(map.getClass().getName());
        return "ok";
    }
    @RequestMapping("/testModelandView")
    public ModelAndView testModelandView(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("testServletAPI","利用modelandview存入的数据");
        mav.setViewName("ok");
        return mav;
    }
}
