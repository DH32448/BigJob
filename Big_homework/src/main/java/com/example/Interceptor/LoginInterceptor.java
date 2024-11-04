package com.example.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * 用于拦截请求，检查用户是否已登录。如果用户未登录，则重定向到登录页面。
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前执行的方法
     * 用于检查用户是否已登录。
     *
     * @param request 请求对象，用于获取请求信息
     * @param response 响应对象，用于设置响应信息
     * @param handler 处理器对象，表示当前请求的处理器
     * @return 如果用户已登录，返回true继续处理请求；否则返回false中断请求处理
     * @throws Exception 可能抛出的异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了@@@@@");

        // 获取登录的信息
        Object user = request.getSession().getAttribute("user");

        // 判断登录信息是否正常
        if (user != null) {
            System.out.println("用户已经登录正常执行");
            return true; // 用户已登录，继续处理请求
        } else {
            System.out.println("用户没有登录，就转发到登录界面");
            // 转发到登录界面
            response.sendRedirect("/home/main");
            return false; // 用户未登录，中断请求处理
        }
    }
}
