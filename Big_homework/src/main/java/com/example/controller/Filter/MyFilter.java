package com.example.controller.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义过滤器
 * 用于拦截所有请求，并进行一些预处理或后处理操作。
 * 在Spring环境中，可以通过以下方式注册和使用这个过滤器：
 * 1. 使用 `@WebFilter` 注解。
 * 2. 在Spring配置文件中声明过滤器。
 * 3. 使用Java配置类来注册过滤器。
 */
@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
public class MyFilter implements Filter {

    /**
     * 构造函数
     * 在过滤器实例化时调用，用于初始化过滤器。
     *
     * 在Spring环境中，可以通过依赖注入来初始化过滤器中的其他Bean。
     */
    public MyFilter() {
        System.out.println("MyFilter 构造");
    }

    /**
     * 过滤器的核心方法
     * 每个请求都会经过此方法，可以在此方法中进行各种处理。
     *
     * @param servletRequest 传入的请求对象
     * @param servletResponse 传入的响应对象
     * @param filterChain 过滤链，用于将请求传递给下一个过滤器或目标资源
     * @throws ServletException 如果发生Servlet相关异常
     * @throws IOException 如果发生IO相关异常
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("进入到过滤器...");

        // 将ServletRequest转换为HttpServletRequest，以便获取更多HTTP请求信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 获取当前请求的会话
        HttpSession session = request.getSession();

        // 获取请求的路径
        String servletPath = request.getServletPath();

        // 打印会话和请求路径，用于调试
        System.out.println("session: " + session);
        System.out.println("请求路径: " + servletPath);

        // 继续过滤链，将请求传递给下一个过滤器或目标资源
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 初始化方法
     * 在过滤器被容器初始化时调用，用于执行一些初始化操作。
     *
     * 在Spring环境中，可以通过依赖注入来初始化过滤器中的其他Bean。
     *
     * @param filterConfig 过滤器配置对象，包含过滤器的初始化参数
     * @throws ServletException 如果发生Servlet相关异常
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 可以在这里进行初始化操作，例如读取配置文件、初始化资源等
        System.out.println("MyFilter 初始化");
    }

    /**
     * 销毁方法
     * 在过滤器被容器销毁时调用，用于执行一些清理操作。
     *
     * 在Spring环境中，可以通过依赖注入来管理过滤器的生命周期。
     */
    @Override
    public void destroy() {
        // 可以在这里进行清理操作，例如释放资源、关闭连接等
        System.out.println("MyFilter 销毁");
    }
}
