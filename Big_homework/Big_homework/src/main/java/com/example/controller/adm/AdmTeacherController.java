package com.example.controller.adm;

import com.example.dao.UserDao;
import com.example.entity.UserEntity;
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
 * # 教师管理
 */
@Controller
@RequestMapping("/adm/teacher")
public class AdmTeacherController {
    @Autowired
    UserDao userDao;
    public AdmTeacherController() {
        System.out.println("AdmTeacherController 构造");
    }

    @RequestMapping("/go2show")
    public String go2show(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        List<UserEntity> userEntityListTeacher = userDao.findByRole(5);
        model.addAttribute("userEntityListTeacher", userEntityListTeacher);
        return "/adm/teacher/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/teacher/go2show";
    }
    @PostMapping("/add")
    public String add(UserEntity userEntity, Model model) {
        userEntity.setRole(1);
        System.out.println(userEntity);
        if (userEntity.getUname() == null || userEntity.getUname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/teacher/go2add";
        }
        userDao.add(userEntity);
        return "forward:/adm/teacher/go2show";
    }
    @GetMapping("/remove")
    public String remove(UserEntity userEntity) {
        System.out.println(userEntity);
        userDao.del(userEntity.getUid());
        return "forward:/adm/teacher/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, UserEntity userEntity) {
        model.addAttribute("action", "update");
        System.out.println(userEntity);
        model.addAttribute("userEntityUpdate", userEntity);
        return "forward:/adm/teacher/go2show";
    }

    @PostMapping("/update")
    public String update(UserEntity userEntity) {
        userEntity.setRole(1);
        System.out.println(userEntity);
        userDao.update(userEntity);
        return "forward:/adm/teacher/go2show";
    }
}
