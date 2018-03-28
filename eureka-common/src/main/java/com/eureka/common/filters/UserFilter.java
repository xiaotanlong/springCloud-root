package com.eureka.common.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 非注解的形式田添加过滤器    感觉比较灵活，需要时才加载 适合多模块 微服务
 @Bean
 public FilterRegistrationBean filterRegistrationBean(){
 FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
 List<String> urlPatterns = new ArrayList<String>();
 TestFilter testFilter = new TestFilter();   //new过滤器
 urlPatterns.add("/web");      //指定需要过滤的url
 filterRegistrationBean.setFilter(testFilter);       //set
 filterRegistrationBean.setUrlPatterns(urlPatterns);     //set
 return filterRegistrationBean;
 }*/
/**
 * @author tjl
 * @version V1.0
 * @Title: 过滤身份信息
 * @Package 过滤器
 * @Description: 过滤身份(用一句话描述该文件做什么)
 * @date 2018-3-28
 */
@Component
@WebFilter(urlPatterns = "/web",filterName = "userFilter")//
// 说明这是一个web过滤器，它拦截的url为/web，过滤器名字为userFilter
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化方法
    }

    /**
     * 初始化（init）和摧毁（destroy）方法一般不会用到，具体使用看下源码便知
       doFilter（）是过滤器的核心
       注意：在实现接口方法之后，我们要转换request和response类型至HttpServlet，否则接下去的操作可能会报错。
       如果过滤通过，执行filterChain.doFilter(request,response);
       说明这个url已经经过了我们的Filter
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截处理
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.printf("过滤器实现");
        filterChain.doFilter(request,response);
        //TODO
    }

    @Override
    public void destroy() {
        //销毁化方法
    }

}
