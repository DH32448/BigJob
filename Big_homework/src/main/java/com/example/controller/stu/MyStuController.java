package com.example.controller.stu;

import com.example.dao.MarkDao;
import com.example.dao.TaskDao;
import com.example.entity.MarkEntity;
import com.example.entity.TaskEntity;
import com.example.entity.UserEntity;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生控制器
 * 用于处理学生相关的请求，包括修改密码、查询课程和查询成绩。
 *
 * @author HXJ
 * # 修改密码
 * # 查询课程
 * # 查询我的成绩
 */
@Controller
@RequestMapping("/stu")
public class MyStuController {

    // 通过@Autowired注解自动注入UserDao、TaskDao和MarkDao
    @Autowired
    private UserDao userDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private MarkDao markDao;

    /**
     * 跳转到修改密码页面
     *
     * @param model 用于向视图传递数据
     * @param session 用于获取当前用户的会话信息
     * @return 返回视图名称
     */
    @RequestMapping("/go2Pwd")
    public String go2Pwd(Model model, HttpSession session) {
        model.addAttribute("action", "update");
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "main"; // 用户未登录，跳转到主页
        }
        if (user.getRole() != 1) {
            return "main"; // 用户角色不是学生，跳转到主页
        }
        return "/stu/show"; // 跳转到修改密码页面
    }

    /**
     * 处理修改密码的POST请求
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param twoNewPwd 确认新密码
     * @param model 用于向视图传递数据
     * @param session 用于获取当前用户的会话信息
     * @return 返回视图名称
     */
    @PostMapping("/update")
    public String pwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String twoNewPwd, Model model, HttpSession session) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null || !userEntity.getPwd().equals(oldPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "旧密码错误");
            return "/stu/show"; // 旧密码错误，返回修改密码页面并显示错误信息
        }

        if (!newPwd.equals(twoNewPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "新密码和确认密码不一致");
            return "/stu/show"; // 新密码和确认密码不一致，返回修改密码页面并显示错误信息
        }

        userEntity.setPwd(newPwd);
        userDao.update(userEntity);  // 更新密码
        model.addAttribute("msg", "密码更新成功");
        return "/main"; // 密码更新成功，跳转到主页
    }

    /**
     * 跳转到查询课程页面
     *
     * @param model 用于向视图传递数据
     * @param session 用于获取当前用户的会话信息
     * @return 返回视图名称
     */
    @GetMapping("/go2Course")
    public String go2Course(Model model, HttpSession session) {
        model.addAttribute("action", "course");
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<TaskEntity> taskDaoByClzno = taskDao.findByClzno(user.getClzno());
        model.addAttribute("taskEntityList", taskDaoByClzno);
        return "/stu/show"; // 跳转到查询课程页面
    }

    /**
     * 跳转到查询成绩页面
     *
     * @param model 用于向视图传递数据
     * @param session 用于获取当前用户的会话信息
     * @return 返回视图名称
     */
    @GetMapping("/go2score")
    public String go2score(Model model, HttpSession session) {
        model.addAttribute("action", "score");
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<MarkEntity> markEntityList = markDao.findBySno(user.getPhone());
        markEntityList.forEach(System.out::println); // 打印成绩列表，用于调试
        model.addAttribute("markEntityList", markEntityList);
        return "/stu/show"; // 跳转到查询成绩页面
    }
}
