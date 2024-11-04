package com.example.controller;

import com.example.Interceptor.LoginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类
 * 用于配置Spring MVC的相关设置，。
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * 重写WebMvcConfigurer接口的addInterceptors方法，用于注册自定义的拦截器。
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建一个新的LoginInterceptor实例并注册到拦截器注册表中
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") // 指定拦截器应用于所有路径
                .excludePathPatterns("/home/*"); // 排除/home下的所有路径
    }
}
