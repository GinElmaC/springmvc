package com.GinElmaC.springmvc.Dao;

import com.GinElmaC.springmvc.Bean.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private static List<User> users;

    static{
        users = new ArrayList<>();
        User u1 = new User(1001l,"张三",1,"zhangsan.com");
        User u2 = new User(1002l,"孙悟空",1,"wukong.com");
        User u3 = new User(1003l,"八戒",1,"bajie.com");
        users.add(u1);
        users.add(u2);
        users.add(u3);
    }

    public List<User> selectAll(){
        return users;
    }
    public void saveone(User user){
        long midlong = users.get(users.size()-1).getId()+1;
        user.setId(midlong);
        users.add(user);
    }
    public User selectById(Long id){
        for(int i = 0;i< users.size();i++){
            if(users.get(i).getId().equals(id)){
                return users.get(i);
            }
        }
        return null;
    }
    public void moditfy(User user){
        for(int i = 0;i<users.size();i++){
            if(users.get(i).getId().equals(user.getId())){
                users.set(i,user);
                break;
            }
        }
    }
}

