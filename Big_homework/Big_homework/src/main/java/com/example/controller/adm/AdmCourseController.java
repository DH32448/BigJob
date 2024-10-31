package com.example.controller.adm;

import com.example.dao.CourseDao;
import com.example.entity.ClzEntity;
import com.example.entity.CourseEntity;
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
 * # 课程管理
 */
@Controller
@RequestMapping("/adm/course")
public class AdmCourseController {
    @Autowired
    CourseDao courseDao;
    public AdmCourseController() {
        System.out.println("AdmCourseController 构造");
    }
    @RequestMapping("go2show")
    public String show(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
        List<CourseEntity> courseEntityList = courseDao.findAll();
        model.addAttribute("courseEntityList", courseEntityList);
        return "adm/course/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/course/go2show";
    }
    @PostMapping("/add")
    public String add(CourseEntity courseEntity, Model model) {
        if (courseEntity.getCno() == null || courseEntity.getCno().isEmpty() ||
                courseEntity.getCname() == null || courseEntity.getCname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/course/go2add";
        }
        courseDao.add(courseEntity);
        return "forward:/adm/course/go2show";
    }
    @GetMapping("/remove")
    public String remove(CourseEntity courseEntity) {
        System.out.println(courseEntity);
        courseDao.remove(courseEntity);
        return "forward:/adm/course/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, CourseEntity courseEntity) {
        model.addAttribute("action", "update");
        model.addAttribute("courseEntityUpdate", courseEntity);
        return "forward:/adm/course/go2show";
    }

    @PostMapping("/update")
    public String update(CourseEntity courseEntity) {
        System.out.println(courseEntity);
        courseDao.update(courseEntity);
        return "forward:/adm/course/go2show";
    }
}
