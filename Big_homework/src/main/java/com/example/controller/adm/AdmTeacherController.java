package com.example.controller.adm;

import com.example.Service.UserService;
import com.example.dao.LargeFileDao;
import com.example.entity.Largefile;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author HJX
 * 教师管理控制器
 * 用于处理与教师管理相关的请求，包括显示教师列表、添加、删除、更新教师信息。
 */
@Controller
@RequestMapping("/adm/teacher")
public class AdmTeacherController {

    @Autowired
    private UserService userService; // 注入用户数据访问对象
    @Autowired
    private LargeFileDao largeFileDao;

    public AdmTeacherController() {
        System.out.println("AdmTeacherController 构造");
    }

    /**
     * 显示教师列表
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
        List<UserEntity> userEntityListTeacher = userService.findByRole(5); // 查询所有教师
        model.addAttribute("userEntityListTeacher", userEntityListTeacher); // 将教师列表添加到模型
        return "/adm/teacher/show"; // 返回教师列表视图
    }

    /**
     * 跳转到添加教师页面
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action", "add"); // 设置操作为添加
        return "forward:/adm/teacher/go2show"; // 转发到显示教师列表页面
    }

    /**
     * 添加教师
     * @param userEntity 待添加的教师实体
     * @param model 用于传递数据到视图
     * @param image 上传的教师照片
     * @return 视图名称
     */
    @PostMapping("/add")
    public String add(UserEntity userEntity, Model model, @RequestParam("image") MultipartFile image) {
        userEntity.setRole(5); // 设置教师角色
        if (userEntity.getUname() == null || userEntity.getUname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！"); // 添加错误信息
            return "forward:/adm/teacher/go2add"; // 转发到添加教师页面
        }
        if (!image.isEmpty()) {
            try {
                // 检查并删除旧照片
                if (userEntity.getPic() != null) {
                    largeFileDao.delete(userEntity.getPic()); // 删除旧照片
                }

                // 上传新照片
                Largefile largefile = new Largefile();
                largefile.setId(UUID.randomUUID().toString()); // 生成唯一ID
                largefile.setFilename(image.getOriginalFilename()); // 设置文件名
                largefile.setContent(image.getBytes()); // 设置文件内容
                largeFileDao.add(largefile); // 保存照片
                userEntity.setPic(largefile.getId()); // 设置教师照片ID
            } catch (IOException e) {
                model.addAttribute("error", "文件上传失败！"); // 添加错误信息
                return "forward:/adm/teacher/go2add"; // 转发到添加教师页面
            }
        }
        userService.add(userEntity); // 添加教师
        model.addAttribute("msg", "添加成功！！"); // 添加成功信息
        return "forward:/adm/teacher/go2show"; // 转发到显示教师列表页面
    }

    /**
     * 删除教师
     * @param userEntity 待删除的教师实体
     * @param model 用于传递数据到视图
     * @return 视图名称
     */
    @GetMapping("/remove")
    public String remove(UserEntity userEntity, Model model) {
        userService.del(userEntity.getUid()); // 删除教师
        model.addAttribute("msg", "删除成功！！"); // 添加成功信息
        return "forward:/adm/teacher/go2show"; // 转发到显示教师列表页面
    }

    /**
     * 跳转到更新教师页面
     * @param model 用于传递数据到视图
     * @param userEntity 待更新的教师实体
     * @return 视图名称
     */
    @GetMapping("/go2update")
    public String go2update(Model model, UserEntity userEntity) {
        model.addAttribute("action", "update"); // 设置操作为更新
        userEntity = userService.findById(userEntity.getUid()); // 查询教师信息
        model.addAttribute("userEntityUpdate", userEntity); // 将教师信息添加到模型
        return "forward:/adm/teacher/go2show"; // 转发到显示教师列表页面
    }

    /**
     * 更新教师
     * @param userEntity 待更新的教师实体
     * @param model 用于传递数据到视图
     * @param image 上传的教师照片
     * @return 视图名称
     */
    @PostMapping("/update")
    public String update(UserEntity userEntity, Model model, @RequestParam("image") MultipartFile image) {
        userEntity.setRole(5); // 设置教师角色
        if (!image.isEmpty()) {
            try {
                // 检查并删除旧照片
                if (userEntity.getPic() != null) {
                    largeFileDao.delete(userEntity.getPic()); // 删除旧照片
                }

                // 上传新照片
                Largefile largefile = new Largefile();
                largefile.setId(UUID.randomUUID().toString()); // 生成唯一ID
                largefile.setFilename(image.getOriginalFilename()); // 设置文件名
                largefile.setContent(image.getBytes()); // 设置文件内容
                largeFileDao.add(largefile); // 保存照片
                userEntity.setPic(largefile.getId()); // 设置教师照片ID
            } catch (IOException e) {
                model.addAttribute("error", "文件上传失败！"); // 添加错误信息
                return "forward:/adm/teacher/go2add"; // 转发到添加教师页面
            }
        }
        userService.update(userEntity); // 更新教师信息
        model.addAttribute("msg", "更新成功！！"); // 添加成功信息
        return "forward:/adm/teacher/go2show"; // 转发到显示教师列表页面
    }
}
