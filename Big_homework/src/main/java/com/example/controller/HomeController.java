package com.example.controller;

import com.example.dao.LargeFileDao;
import com.example.dao.UserDao;
import com.example.entity.Largefile;
import com.example.entity.UserEntity;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    UserDao userDao;
    @Autowired
    LargeFileDao largeFileDao;
    public HomeController() {
        System.out.println("HomeController 构造");
    }
    @RequestMapping("/main")
    public String domain(HttpSession session) {
        return "/main";
    }@PostMapping("/login")
    public String login(UserEntity userEntity, HttpSession session, Model model) {
        UserEntity user  = userDao.findByUserByPhone(userEntity.getPhone());

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
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "forward:/home/main";
    }
    @GetMapping("/showimg/{id}")
    public void dispaly(@PathVariable String id, HttpServletResponse response) {
        if (id == null) return;
        Largefile lg = largeFileDao.findOne(id);
        byte[] buf = (byte[])lg.getContent();   //字节数组方式
        try {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
