package com.example.controller.adm;

import com.example.dao.LargeFileDao;
import com.example.dao.UserDao;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author HJX
 * # 教师管理
 */
@Controller
@RequestMapping("/adm/teacher")
public class AdmTeacherController {
    @Autowired
    UserDao userDao;
    @Autowired
    LargeFileDao largeFileDao;
    public AdmTeacherController() {
        System.out.println("AdmTeacherController 构造");
    }

    @RequestMapping("/go2show")
    public String go2show(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {return "main";}
        if (user.getRole() != 9) {return "main";}
        List<UserEntity> userEntityListTeacher = userDao.findByRole(5);
        model.addAttribute("userEntityListTeacher", userEntityListTeacher);
        return "/adm/teacher/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/teacher/go2show";
    }
    @PostMapping("/add")
    public String add(UserEntity userEntity, Model model,@RequestParam("image") MultipartFile image) {
        userEntity.setRole(5);
        if (userEntity.getUname() == null || userEntity.getUname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/teacher/go2add";
        }
        if (!image.isEmpty()) {
            try {
                // 检查并删除旧照片
                if (userEntity.getPic() != null) {
                    largeFileDao.delete(userEntity.getPic());
                }

                // 上传新照片
                Largefile largefile = new Largefile();
                largefile.setId(UUID.randomUUID().toString());
                largefile.setFilename(image.getOriginalFilename());
                largefile.setContent(image.getBytes());
                System.out.println(largeFileDao.add(largefile));
                userEntity.setPic(largefile.getId());
            } catch (IOException e) {
                model.addAttribute("error", "文件上传失败！");
                return "forward:/adm/student/go2show";
            }
        }
        userDao.add(userEntity);
        model.addAttribute("msg", "添加成功！！");

        return "forward:/adm/teacher/go2show";
    }
    @GetMapping("/remove")
    public String remove(UserEntity userEntity, Model model) {
        userDao.del(userEntity.getUid());
        model.addAttribute("msg", "删除成功！！");

        return "forward:/adm/teacher/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, UserEntity userEntity) {
        model.addAttribute("action", "update");
        userEntity = userDao.findById(userEntity.getUid());
        model.addAttribute("userEntityUpdate", userEntity);
        return "forward:/adm/teacher/go2show";
    }

    @PostMapping("/update")
    public String update(UserEntity userEntity, Model model,@RequestParam("image") MultipartFile image) {
        userEntity.setRole(5);
        if (!image.isEmpty()) {
            try {
                // 检查并删除旧照片
                if (userEntity.getPic() != null) {
                    largeFileDao.delete(userEntity.getPic());
                }

                // 上传新照片
                Largefile largefile = new Largefile();
                largefile.setId(UUID.randomUUID().toString());
                largefile.setFilename(image.getOriginalFilename());
                largefile.setContent(image.getBytes());
                System.out.println(largeFileDao.add(largefile));
                userEntity.setPic(largefile.getId());
            } catch (IOException e) {
                model.addAttribute("error", "文件上传失败！");
                return "forward:/adm/student/go2show";
            }
        }
        userDao.update(userEntity);
        model.addAttribute("msg", "更新成功！！");
        return "forward:/adm/teacher/go2show";
    }
}
