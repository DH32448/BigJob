package com.example.controller.adm;

import com.example.Service.ClzService;
import com.example.entity.ClzEntity;
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
 * 班级管理控制器
 * 用于处理与班级相关的请求，包括显示班级列表、添加、删除和更新班级信息。
 */
@Controller
@RequestMapping("/adm/clz")
public class AdmClzController {

    @Autowired
    private ClzService clzService; // 注入班级数据访问对象

    public AdmClzController() {
        System.out.println("AdmClzController 构造");
    }

    /**
     * 显示班级列表
     * @param model 用于传递数据到视图
     * @param session 用于获取当前用户的会话信息
     * @return 视图名称
     */
    @RequestMapping("/go2show")
    public String domain(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user"); // 获取当前用户
        if (user == null) { // 用户未登录
            return "main"; // 重定向到主页面
        }
        if (user.getRole() != 9) { // 用户角色不是管理员
            return "main"; // 重定向到主页面
        }
        List<ClzEntity> clzEntityList = clzService.findAll(); // 查询所有班级
        model.addAttribute("clzEntityList", clzEntityList); // 将班级列表添加到模型
        return "/adm/clz/show"; // 返回班级列表视图
    }

    /**
     * 跳转到添加班级页面
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action", "add"); // 设置操作为添加
        return "forward:/adm/clz/go2show"; // 转发到显示班级列表页面
    }

    /**
     * 添加班级
     * @param clzEntity 待添加的班级实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/add")
    public String add(ClzEntity clzEntity, Model model) {
        if (clzEntity.getClzno() == null || clzEntity.getClzno().isEmpty() ||
                clzEntity.getClzname() == null || clzEntity.getClzname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！"); // 添加错误信息
            return "forward:/adm/clz/go2add"; // 转发到添加班级页面
        }
        clzService.add(clzEntity); // 添加班级
        model.addAttribute("msg", "添加成功！！"); // 添加成功信息
        return "forward:/adm/clz/go2show"; // 转发到显示班级列表页面
    }

    /**
     * 删除班级
     * @param clzEntity 待删除的班级实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @GetMapping("/remove")
    public String remove(ClzEntity clzEntity, Model model) {
        clzService.remove(clzEntity); // 删除班级
        model.addAttribute("msg", "删除成功！！"); // 添加成功信息
        return "forward:/adm/clz/go2show"; // 转发到显示班级列表页面
    }

    /**
     * 跳转到更新班级页面
     * @param model 用于传递数据到视图
     * @param clzEntity 待更新的班级实体
     * @return 视图名称
     */
    @GetMapping("/go2update")
    public String go2update(Model model, ClzEntity clzEntity) {
        model.addAttribute("action", "update"); // 设置操作为更新
        model.addAttribute("clzEntityUpdate", clzEntity); // 将待更新的班级实体添加到模型
        return "forward:/adm/clz/go2show"; // 转发到显示班级列表页面
    }

    /**
     * 更新班级
     * @param clzEntity 待更新的班级实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @PostMapping("/update")
    public String update(ClzEntity clzEntity, Model model) {
        clzService.update(clzEntity); // 更新班级
        model.addAttribute("msg", "更新成功！！"); // 添加成功信息
        return "forward:/adm/clz/go2show"; // 转发到显示班级列表页面
    }
}
