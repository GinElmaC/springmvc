package com.GinElmaC.springmvc.Controller;

import com.GinElmaC.springmvc.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll(){
        System.out.println("finding all .......");
        return "ok";
    }
//    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
//    public String getone(@PathVariable("id") String id){
//        System.out.println("finging this ......,id="+id);
//        return "ok";
//    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertOne(@RequestParam("username")String username, @RequestParam("password") String password, @RequestParam("age") Integer age){
        User user = new User(username,password,age);
        System.out.println(user);
        return "ok";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String modify(User user){
        System.out.println("modifying......");
        System.out.println(user);
        return "ok";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id")String id){
        System.out.println("deleting......id:"+id);
        return "ok";
    }
}
