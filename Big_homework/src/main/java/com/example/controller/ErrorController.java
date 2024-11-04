package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理控制器
 * 用于捕获并处理整个应用中抛出的异常，统一处理异常并返回友好的错误页面。
 */
@ControllerAdvice
public class ErrorController {

    /**
     * 异常处理方法
     * 用于捕获所有类型的异常，并将异常信息传递给视图。
     *
     * @param e 抛出的异常对象
     * @param model 用于向视图传递数据
     * @return 返回视图名称
     */
    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model) {
        // 将异常信息添加到模型中，以便在视图中显示
        model.addAttribute("error", e.getMessage());

        // 返回错误页面的视图名称
        return "/err";
    }
}
