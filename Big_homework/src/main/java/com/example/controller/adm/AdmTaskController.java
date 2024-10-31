package com.example.controller.adm;

import com.example.dao.ClzDao;
import com.example.dao.CourseDao;
import com.example.dao.TaskDao;
import com.example.dao.UserDao;
import com.example.entity.ClzEntity;
import com.example.entity.CourseEntity;
import com.example.entity.TaskEntity;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
 * # 排课管理
 */
@Controller
@RequestMapping("/adm/task")
public class AdmTaskController {
    @Autowired
    private ClzDao clzDao;

    public AdmTaskController() {
        System.out.println("AdmTaskController 构造");
    }
    @Autowired
    TaskDao taskDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;

    @RequestMapping("/go2show")
    public String go2show(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {return "main";}
        if (user.getRole() != 9) {return "main";}
        List<TaskEntity> taskEntityList = taskDao.findAll();
        model.addAttribute("taskEntityList", taskEntityList);
        return "/adm/task/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        //发送教师id
        List<UserEntity> go2addTeaByRole = userDao.findByRole(5);
        go2addTeaByRole.forEach(System.out::println);
        model.addAttribute("go2addTeaUserByRole", go2addTeaByRole);
        //发送班级
        List<ClzEntity> go2addTeaClz = clzDao.findAll();
        model.addAttribute("go2addTeaClz", go2addTeaClz);
        //发送课程
        List<CourseEntity> go2addTeaCourse = courseDao.findAll();
        model.addAttribute("go2addTeaCourse", go2addTeaCourse);
        return "forward:/adm/task/go2show";
    }
    @PostMapping("/add")
    public String add(TaskEntity taskEntity, Model model) {
        System.out.println(taskEntity);
        if (taskEntity.getTid() == 0) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/task/go2add";
        }
        try {
            taskDao.add(taskEntity);
            model.addAttribute("msg", "添加成功！！");

            return "forward:/adm/task/go2show";
        } catch (Exception e) {
            model.addAttribute("error", "该教师已安排此课程，不能重复添加！");
            return "forward:/adm/task/go2add";
        }
    }
    @GetMapping("/remove")
    public String remove(TaskEntity taskEntity, Model model) {
        taskDao.remove(taskEntity);
        model.addAttribute("msg", "删除成功！！");

        return "forward:/adm/task/go2show";
    }
}
