package com.example.controller.adm;

import com.example.dao.ClzDao;
import com.example.entity.ClzEntity;
import com.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HJX
 * # 班级管理
 */
@Controller
@RequestMapping("/adm/clz")
public class AdmClzController {
    @Autowired
    ClzDao clzDao;
    public AdmClzController() {
        System.out.println("AdmClzController 构造");
    }
    @RequestMapping("/go2show")
    public String domain(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {return "main";}
        if (user.getRole() != 9) {return "main";}
        List<ClzEntity> clzEntityList = clzDao.findAll();
        model.addAttribute("clzEntityList", clzEntityList);
        return "/adm/clz/show";
    }
    @RequestMapping("/go2add")
    public String go2add(Model model) {
        model.addAttribute("action","add");
        return "forward:/adm/clz/go2show";
    }
    @PostMapping("/add")
    public String add(ClzEntity clzEntity,Model model) {
        if (clzEntity.getClzno() == null || clzEntity.getClzno().isEmpty() ||
                clzEntity.getClzname() == null || clzEntity.getClzname().isEmpty()) {
            model.addAttribute("error", "添加信息不能为空！");
            return "forward:/adm/clz/go2add";
        }
        clzDao.add(clzEntity);
        model.addAttribute("msg", "添加成功！！");

        return "forward:/adm/clz/go2show";
    }
    @GetMapping("/remove")
    public String remove(ClzEntity clzEntity,Model model) {
        clzDao.remove(clzEntity);
        model.addAttribute("msg", "删除成功！！");
        return "forward:/adm/clz/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, ClzEntity clzEntity) {
        model.addAttribute("action", "update");
        model.addAttribute("clzEntityUpdate", clzEntity);
        return "forward:/adm/clz/go2show";
    }
    @PostMapping("/update")
    public String update(ClzEntity clzEntity,Model model) {
        clzDao.update(clzEntity);
        model.addAttribute("msg", "添加成功！！");
        return "forward:/adm/clz/go2show";
    }
}
