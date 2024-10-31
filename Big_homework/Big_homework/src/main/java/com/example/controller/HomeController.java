package com.example.controller;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    UserDao userDao;
    public HomeController() {
        System.out.println("HomeController 构造");
    }
    @RequestMapping("/main")
    public String domain() {
        return "main";
    }
    @PostMapping("/login")
    public String login(UserEntity userEntity, HttpSession session, Model model) {
        UserEntity user  = userDao.findByUserByPhone(userEntity.getPhone());

        System.out.println(userEntity);
        if (user==null){
            model.addAttribute("error","请登录");
            return "forward:/home/main";
        }
        System.out.println(user);
        if (!userEntity.getPhone().equals(user.getPhone())) {
            model.addAttribute("error","手机号不存在");
            return "forward:/home/main";
        }
        if (!userEntity.getPwd().equals(user.getPwd())) {
            model.addAttribute("error","密码错误");
            return "forward:/home/main";
        }
       session.setAttribute("user",user);


        model.addAttribute("user",user);
        return "forward:/home/main";
    }
}
