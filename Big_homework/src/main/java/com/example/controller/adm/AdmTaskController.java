package com.example.controller.adm;

import com.example.Service.ClzService;
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

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HJX
 * 排课管理控制器
 * 用于处理与排课相关的请求，包括显示排课列表、添加、删除排课信息。
 */
@Controller
@RequestMapping("/adm/task")
public class AdmTaskController {

    @Autowired
    private ClzService clzService; // 注入班级数据访问对象

    public AdmTaskController() {
        System.out.println("AdmTaskController 构造");
    }

    @Autowired
    private TaskDao taskDao; // 注入任务数据访问对象
    @Autowired
    private UserDao userDao; // 注入用户数据访问对象
    @Autowired
    private CourseDao courseDao; // 注入课程数据访问对象

    /**
     * 显示排课列表
     * @param model 用于传递数据到视图
     * @param session 用于获取当前用户的会话信息
     * @return 视图名称
     */
    @RequestMapping("/go2show")
    public String go2show(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user"); // 获取当前用户
        if (user == null) { // 用户未登录
            return "main"; // 重定向到主页面
        }
        if (user.getRole() != 9) { // 用户角色不是管理员
            return "main"; // 重定向到主页面
        }
        List<TaskEntity> taskEntityList = taskDao.findAll(); // 查询所有排课信息
        model.addAttribute("taskEntityList", taskEntityList); // 将排课列表添加到模型
        return "/adm/task/show"; // 返回排课列表视图
    }

    /**
     * 跳转到添加排课页面
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action", "add"); // 设置操作为添加
        // 发送教师ID
        List<UserEntity> go2addTeaByRole = userDao.findByRole(5); // 查询所有教师
        go2addTeaByRole.forEach(System.out::println); // 打印教师信息
        model.addAttribute("go2addTeaUserByRole", go2addTeaByRole); // 将教师列表添加到模型
        // 发送班级
        List<ClzEntity> go2addTeaClz = clzService.findAll(); // 查询所有班级
        model.addAttribute("go2addTeaClz", go2addTeaClz); // 将班级列表添加到模型
        // 发送课程
        List<CourseEntity> go2addTeaCourse = courseDao.findAll(); // 查询所有课程
        model.addAttribute("go2addTeaCourse", go2addTeaCourse); // 将课程列表添加到模型
        return "forward:/adm/task/go2show"; // 转发到显示排课列表页面
    }

    /**
     * 添加排课
     * @param taskEntity 待添加的排课实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/add")
    public String add(TaskEntity taskEntity, Model model) {
        System.out.println(taskEntity); // 打印排课实体信息
        if (taskEntity.getTid() == 0) {
            model.addAttribute("error", "添加信息不能为空！"); // 添加错误信息
            return "forward:/adm/task/go2add"; // 转发到添加排课页面
        }
        try {
            taskDao.add(taskEntity); // 添加排课
            model.addAttribute("msg", "添加成功！！"); // 添加成功信息
            return "forward:/adm/task/go2show"; // 转发到显示排课列表页面
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "该教师已安排此课程，不能重复添加！"); // 添加错误信息
            return "forward:/adm/task/go2add"; // 转发到添加排课页面
        } catch (Exception e) {
            model.addAttribute("error", "添加失败，请检查输入信息！"); // 添加错误信息
            return "forward:/adm/task/go2add"; // 转发到添加排课页面
        }
    }

    /**
     * 删除排课
     * @param taskEntity 待删除的排课实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @GetMapping("/remove")
    public String remove(TaskEntity taskEntity, Model model) {
        taskDao.remove(taskEntity); // 删除排课
        model.addAttribute("msg", "删除成功！！"); // 添加成功信息
        return "forward:/adm/task/go2show"; // 转发到显示排课列表页面
    }
}
