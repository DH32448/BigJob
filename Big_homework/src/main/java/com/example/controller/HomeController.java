package com.example.controller;

import com.example.dao.LargeFileDao;
import com.example.dao.UserDao;
import com.example.entity.Largefile;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 主页控制器
 * 用于处理与主页相关的请求，包括登录、登出、显示主页面和显示图片。
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    // 通过@Autowired注解自动注入UserDao和LargeFileDao
    @Autowired
    private UserDao userDao;
    @Autowired
    private LargeFileDao largeFileDao;

    /**
     * 构造函数
     * 用于初始化HomeController实例时打印构造信息。
     */
    public HomeController() {
        System.out.println("HomeController 构造");
    }

    /**
     * 跳转到主页面
     *
     * @param session 用于获取当前用户的会话信息
     * @return 返回视图名称
     */
    @RequestMapping("/main")
    public String domain(HttpSession session) {
        return "/main"; // 返回主页面的视图名称
    }

    /**
     * 处理登录请求
     *
     * @param userEntity 用户提交的登录信息
     * @param session 用于存储当前用户的会话信息
     * @param model 用于向视图传递数据
     * @return 返回视图名称
     */
    @PostMapping("/login")
    public String login(UserEntity userEntity, HttpSession session, Model model) {
        UserEntity user = userDao.findByUserByPhone(userEntity.getPhone());

        if (user == null) {
            model.addAttribute("error", "请登录");
            return "forward:/home/main"; // 用户不存在，返回主页面并显示错误信息
        }

        System.out.println(user);

        if (!userEntity.getPhone().equals(user.getPhone())) {
            model.addAttribute("error", "手机号不存在");
            return "forward:/home/main"; // 手机号不存在，返回主页面并显示错误信息
        }

        if (!userEntity.getPwd().equals(user.getPwd())) {
            model.addAttribute("error", "密码错误");
            return "forward:/home/main"; // 密码错误，返回主页面并显示错误信息
        }

        session.setAttribute("user", user); // 将用户信息存储到会话中

        model.addAttribute("user", user);
        return "forward:/home/main"; // 登录成功，返回主页面
    }

    /**
     * 处理登出请求
     *
     * @param session 用于移除当前用户的会话信息
     * @return 返回视图名称
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user"); // 移除会话中的用户信息
        return "forward:/home/main"; // 返回主页面
    }

    /**
     * 显示图片
     *
     * @param id 图片的唯一标识
     * @param response 用于设置响应的内容类型和输出流
     */
    @GetMapping("/showimg/{id}")
    public void display(@PathVariable String id, HttpServletResponse response) {
        if (id == null) return; // 如果id为空，直接返回

        Largefile lg = largeFileDao.findOne(id); // 根据id查找图片
        byte[] buf = (byte[]) lg.getContent(); // 获取图片的字节数组

        try {
            response.setContentType("image/jpeg"); // 设置响应的内容类型为JPEG图像
            response.getOutputStream().write(buf); // 将图片数据写入响应输出流
        } catch (Exception e) {
            e.printStackTrace(); // 捕获并打印异常信息
        }
    }
}
