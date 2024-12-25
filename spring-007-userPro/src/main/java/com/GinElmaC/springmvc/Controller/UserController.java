package com.GinElmaC.springmvc.Controller;

import com.GinElmaC.springmvc.Bean.User;
import com.GinElmaC.springmvc.Dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String list(Model model){
        //查数据库获取用户列表
        List<User> users=  userDao.selectAll();
        //将用户列表存到request域
        model.addAttribute("users",users);
        //转发到视图
        return "user_list";
    }
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String insertone(User user){
        userDao.saveone(user);
        return "redirect:/user";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id")Long id, Model model){
        User user = userDao.selectById(id);
        model.addAttribute("user",user);
        return "user_edit";
    }
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String modifty(User user){
        userDao.moditfy(user);
        return "redirect:/user";
    }
}
