package com.example.controller.stu;

import com.example.dao.MarkDao;
import com.example.dao.TaskDao;
import com.example.entity.MarkEntity;
import com.example.entity.TaskEntity;
import com.example.entity.UserEntity;
import com.example.dao.UserDao;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.font.MultipleMaster;
import java.util.List;

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
    @Autowired
    TaskDao taskDao;
    @Autowired
    MarkDao markDao;

    @RequestMapping("/go2Pwd")
    public String go2Pwd(Model model,HttpSession  session) {
        model.addAttribute("action", "update");
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {return "main";}
        if (user.getRole() != 1) {return "main";}
        return "/stu/show";
    }

    @PostMapping("/update")
    public String pwd(String oldPwd, String newPwd, String twoNewPwd, Model model, HttpSession session) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null || !userEntity.getPwd().equals(oldPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "旧密码错误");
            return "/stu/show";
        }

        if (!newPwd.equals(twoNewPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "新密码和确认密码不一致");
            return "/stu/show";
        }

        userEntity.setPwd(newPwd);
        userDao.update(userEntity);  // 更新密码
        model.addAttribute("msg", "密码更新成功");
        return "/main";

    }
    @GetMapping("/go2Course")
    public String go2Course(Model model, HttpSession session) {
        model.addAttribute("action", "course");
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<TaskEntity> taskDaoByClzno = taskDao.findByClzno(user.getClzno());
        model.addAttribute("taskEntityList", taskDaoByClzno);
        return "/stu/show";
    }
    @GetMapping("/go2score")
    public String go2score(Model model, HttpSession session) {
        model.addAttribute("action", "score");
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<MarkEntity> markEntityList = markDao.findBySno(user.getPhone());
        markEntityList.forEach(System.out::println);
        model.addAttribute("markEntityList", markEntityList);
         return "/stu/show";
    }
}