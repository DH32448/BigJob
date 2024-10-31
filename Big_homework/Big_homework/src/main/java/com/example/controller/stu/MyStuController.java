package com.example.controller.stu;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
import org.apache.catalina.Session;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author HXJ
 * # 修改密码
 * # 查询课程
 * # 查询我的成绩
 */
@Controller
@RequestMapping("/stu")
public class MyStuController {
    @Autowired
    UserDao userDao;
    public MyStuController() {
        System.out.println("MyStuController 构造");
    }
    @RequestMapping("/go2show")
    public String go2show(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        model.addAttribute("action","update");
        return "/stu/show";
    }
    @RequestMapping("/go2Pwd")
    public String go2Pwd(Model model) {
        model.addAttribute("action","update");

        //return "forward:/stu/go2show";
        return "/stu/show";
    }
    @PostMapping("/update")
    public String pwd(String uid,String oldPwd, String newPwd, String twoNewPwd ,Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(uid);
        System.out.println("old: " + oldPwd);
        System.out.println("new: " + newPwd);
        System.out.println("twoNew: " + twoNewPwd);
        if (newPwd.equals(twoNewPwd)) {
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            System.out.println(userEntity);
            userEntity.setPwd(newPwd);
            System.out.println(userEntity);
            userDao.updatePwd(userEntity);
            return "forward:/stu/go2show";
        }
        return "forward:/stu/update";
    }
}
