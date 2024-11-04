package com.example.controller.adm;

import com.example.Service.CourseService;
import com.example.entity.CourseEntity;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HJX
 * 课程管理控制器
 * 用于处理与课程相关的请求，包括显示课程列表、添加、删除和更新课程信息。
 */
@Controller
@RequestMapping("/adm/course")
public class AdmCourseController {
    @Autowired
    private CourseService courseService;


    public AdmCourseController() {
        System.out.println("AdmCourseController 构造");
    }

    /**
     * 显示课程列表
     * @param model 用于传递数据到视图
     * @param session 用于获取当前用户的会话信息
     * @return 视图名称
     */
    @RequestMapping("go2show")
    public String show(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user"); // 获取当前用户
        if (user == null) { // 用户未登录
            return "main"; // 重定向到主页面
        }
        if (user.getRole() != 9) { // 用户角色不是管理员
            return "main"; // 重定向到主页面
        }
        List<CourseEntity> courseEntityList = courseService.findAll(); // 查询所有课程
        model.addAttribute("courseEntityList", courseEntityList); // 将课程列表添加到模型
        return "adm/course/show"; // 返回课程列表视图
    }

    /**
     * 跳转到添加课程页面
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action", "add"); // 设置操作为添加
        return "forward:/adm/course/go2show"; // 转发到显示课程列表页面
    }

    /**
     * 添加课程
     * @param courseEntity 待添加的课程实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/add")
    public String add(CourseEntity courseEntity, Model model) {
        if (courseEntity.getCno() == null || courseEntity.getCno().isEmpty() ||
                courseEntity.getCname() == null || courseEntity.getCname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！"); // 添加错误信息
            return "forward:/adm/course/go2add"; // 转发到添加课程页面
        }
        try {
            courseService.add(courseEntity); // 添加课程
            model.addAttribute("msg", "添加成功！！"); // 添加成功信息
        } catch (Exception e) {
            model.addAttribute("error", "课程已存在"); // 添加错误信息
            model.addAttribute("action", "add"); // 设置操作为添加
            return "forward:/adm/course/go2add"; // 转发到添加课程页面
        }
        return "forward:/adm/course/go2show"; // 转发到显示课程列表页面
    }

    /**
     * 删除课程
     * @param courseEntity 待删除的课程实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @GetMapping("/remove")
    public String remove(CourseEntity courseEntity, Model model) {
        courseService.remove(courseEntity); // 删除课程
        model.addAttribute("msg", "删除成功！！"); // 添加成功信息
        return "forward:/adm/course/go2show"; // 转发到显示课程列表页面
    }

    /**
     * 跳转到更新课程页面
     * @param model 用于传递数据到视图
     * @param courseEntity 待更新的课程实体
     * @return 视图名称
     */
    @GetMapping("/go2update")
    public String go2update(Model model, CourseEntity courseEntity) {
        model.addAttribute("action", "update"); // 设置操作为更新
        model.addAttribute("courseEntityUpdate", courseEntity); // 将待更新的课程实体添加到模型
        return "forward:/adm/course/go2show"; // 转发到显示课程列表页面
    }

    /**
     * 更新课程
     * @param courseEntity 待更新的课程实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/update")
    public String update(CourseEntity courseEntity, Model model) {
        courseService.update(courseEntity); // 更新课程
        model.addAttribute("msg", "更新成功！！"); // 添加成功信息
        return "forward:/adm/course/go2show"; // 转发到显示课程列表页面
    }
}
