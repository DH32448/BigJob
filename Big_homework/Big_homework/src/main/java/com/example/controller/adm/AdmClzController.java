package com.example.controller.adm;

import com.example.dao.ClzDao;
import com.example.entity.ClzEntity;
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
    public String domain(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {return "main";}
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
        return "forward:/adm/clz/go2show";
    }
    @GetMapping("/remove")
    public String remove(ClzEntity clzEntity) {
        System.out.println(clzEntity);
        clzDao.remove(clzEntity);
        return "forward:/adm/clz/go2show";
    }
    @GetMapping("/go2update")
    public String go2update(Model model, ClzEntity clzEntity) {
        model.addAttribute("action", "update");
        System.out.println(clzEntity);
        model.addAttribute("clzEntityUpdate", clzEntity);
        return "forward:/adm/clz/go2show";
    }
    @PostMapping("/update")
    public String update(ClzEntity clzEntity) {
        clzDao.update(clzEntity);
        return "forward:/adm/clz/go2show";
    }
}
