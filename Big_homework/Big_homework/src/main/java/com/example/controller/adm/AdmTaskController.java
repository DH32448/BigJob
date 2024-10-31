package com.example.controller.adm;

import com.example.dao.TaskDao;
import com.example.entity.TaskEntity;
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
 * # 排课管理
 */
@Controller
@RequestMapping("/adm/task")
public class AdmTaskController {
    public AdmTaskController() {
        System.out.println("AdmTaskController 构造");
    }
    @Autowired
    TaskDao taskDao;
    @RequestMapping("/go2show")
    public String go2show(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        List<TaskEntity> taskEntityList = taskDao.findAll();
        model.addAttribute("taskEntityList", taskEntityList);
        return "/adm/task/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/task/go2show";
    }
    @PostMapping("/add")
    public String add(TaskEntity taskEntity, Model model) {
        System.out.println(taskEntity);
        if (taskEntity.getTid() == 0) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/task/go2add";
        }
        taskDao.add(taskEntity);
        return "forward:/adm/task/go2show";
    }
    @GetMapping("/remove")
    public String remove(TaskEntity taskEntity) {
        System.out.println(taskEntity);
        taskDao.remove(taskEntity);
        return "forward:/adm/task/go2show";
    }
}
