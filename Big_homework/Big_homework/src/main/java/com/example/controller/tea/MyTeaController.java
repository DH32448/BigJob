package com.example.controller.tea;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author HJX
 * # 修改我的密码
 * # 登分
 */
@Controller
@RequestMapping("/tea")
public class MyTeaController {
    @Autowired
    UserDao userDao;
    public MyTeaController() {
        System.out.println("MyTeaController 构造");
    }
    @RequestMapping("/go2show")
    public String go2show(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        return "/tea/show";
    }
    //修改密码
    @RequestMapping("/go2Pwd")
    public String go2Pwd(Model model) {
        model.addAttribute("action","update");
        return "forward:/tea/go2show";
    }
    //登分
    @PostMapping("/update")
    public String pwd(String uid,String oldPwd, String newPwd, String twoNewPwd ,Model model) {
        System.out.println(uid);
        System.out.println("old: " + oldPwd);
        System.out.println("new: " + newPwd);
        System.out.println("twoNew: " + twoNewPwd);
        if (newPwd.equals(twoNewPwd) && !newPwd.isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setPwd(newPwd);
            userDao.updatePwd(userEntity);
            return "forward:/stu/go2show";
        }
        return "forward:/tea/update";
    }
}
