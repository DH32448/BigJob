package com.example.controller.tea;

import com.example.Service.CourseService;
import com.example.Service.MarkService;
import com.example.Service.TaskService;
import com.example.Service.UserService;
import com.example.entity.MarkEntity;
import com.example.entity.TaskEntity;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author HJX
 * # 修改我的密码
 * # 登分
 */
@Controller
@RequestMapping("/tea")
public class MyTeaController {
    @Autowired
    UserService userService;
    @Autowired
    MarkService markService;
    @Autowired
    TaskService taskService;
    @Autowired
    CourseService courseService;
    public MyTeaController() {
        System.out.println("MyTeaController 构造");
    }

    //修改密码
    @RequestMapping("/go2Pwd")
    public String go2Pwd(Model model) {
        model.addAttribute("action", "update");
        return "/tea/show";  // 返回显示密码更新页面
    }
    @PostMapping("/update")
    public String pwd(String uid,String oldPwd, String newPwd, String twoNewPwd ,Model model, HttpSession session) {
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null || !userEntity.getPwd().equals(oldPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "旧密码错误");
            return "forward:/tea/update";
        }

        if (!newPwd.equals(twoNewPwd)) {
            model.addAttribute("action", "update");
            model.addAttribute("error", "新密码和确认密码不一致");
            return "forward:/tea/update";
        }
        userEntity.setPwd(newPwd);
        userService.update(userEntity);  // 更新密码
        model.addAttribute("msg", "密码更新成功");
        return "/main";

    }
    @GetMapping("/go2clz")
    public String go2score(Model model,HttpSession session) {
        model.addAttribute("action", "score");
        UserEntity user = (UserEntity) session.getAttribute("user");
        List<TaskEntity> taskEntityList = taskService.findByTid(user.getUid());
        model.addAttribute("taskEntityList", taskEntityList);
        return "/tea/show";
    }
    @RequestMapping("/go2score/{clzno}/{cno}")
    public String go2score(Model model, @PathVariable("clzno") String clzno, @PathVariable("cno") String cno) {
        model.addAttribute("action", "score");
        List<UserEntity> markEntityListScore2 = markService.findByClzno2(cno,clzno);
        model.addAttribute("markEntityListScore2", markEntityListScore2);
        return "/tea/show";
    }


    @PostMapping("/addscore")
    public String score(MarkEntity markEntity,String phone,
                              String clzno,
                              Model model,HttpSession session) {
        model.addAttribute("action", "addscore");
        UserEntity user = (UserEntity) session.getAttribute("user");
        MarkEntity mark = new MarkEntity();
        mark.setSno(phone);
        mark.setCno(markEntity.getCno());
        mark.setScore(markEntity.getScore());
        mark.setTid(user.getUid());
        mark.setTpost(new Date());
        int result = markService.add(mark);
        model.addAttribute("error", result > 0 ? "分数提交成功" : "分数提交失败");

        return "forward:/tea/go2score/"+clzno+"/"+markEntity.getCno();
    }
    @PostMapping("/updatescore")
    public String updatescore(MarkEntity markEntity,
                              String clzno,String cno, String phone,Model model,HttpSession session) {
        model.addAttribute("action", "updatescore");
        UserEntity user = (UserEntity) session.getAttribute("user");
        markEntity.setTpost(new Date());
        markEntity.setTid(user.getUid());
        markEntity.setCno(cno);
        markEntity.setSno(phone);
        markService.update(markEntity);
        System.out.println(markEntity + "==============");
        return "forward:/tea/go2score/"+clzno+"/"+markEntity.getCno();
    }
}
