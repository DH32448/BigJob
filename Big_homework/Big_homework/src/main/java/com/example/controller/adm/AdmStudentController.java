package com.example.controller.adm;

import com.example.dao.UserDao;
import com.example.entity.CourseEntity;
import com.example.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HJX
 * # 学生管理
 */
@Controller
@RequestMapping("/adm/student")
public class AdmStudentController {
    @Autowired
    UserDao userDao;
    public AdmStudentController() {
        System.out.println("AdmStudentController 构造");
    }
    @RequestMapping("/go2show")
    public String go2show(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        List<UserEntity> userEntityListStudent = userDao.findByRole(1);
        model.addAttribute("userEntityListStudent", userEntityListStudent);
        return "/adm/student/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/student/go2show";
    }
    @PostMapping("/add")
    public String add(UserEntity userEntity, Model model) {
        userEntity.setRole(1);
        System.out.println(userEntity);
        if (userEntity.getUname() == null || userEntity.getUname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/student/go2add";
        }
        userDao.add(userEntity);
        return "forward:/adm/student/go2show";
    }
    @GetMapping("/remove")
    public String remove(UserEntity userEntity) {
        System.out.println(userEntity);
        userDao.del(userEntity.getUid());
        return "forward:/adm/student/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, UserEntity userEntity) {
        model.addAttribute("action", "update");
        System.out.println(userEntity);
        model.addAttribute("userEntityUpdate", userEntity);
        return "forward:/adm/student/go2show";
    }

    @PostMapping("/update")
    public String update(UserEntity userEntity) {
        userEntity.setRole(1);
        System.out.println(userEntity);
        userDao.update(userEntity);
        return "forward:/adm/student/go2show";
    }
}
