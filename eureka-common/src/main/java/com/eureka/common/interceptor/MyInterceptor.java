package com.eureka.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *拦截器只能通过 在拦截器组中添加多个拦截器
 public class WarApplication {

 public static void main(String[] args) {
   SpringApplication.run(WarApplication.class, args);
 }

 //mvc控制器
 //@Configuration
 static class WebMvcConfigurer extends WebMvcConfigurerAdapter{
 //增加拦截器
 public void addInterceptors(InterceptorRegistry registry){
 registry.addInterceptor(new MyInterceptor())    //指定拦截器类
 .addPathPatterns("/Handles");        //指定该类拦截的url
 }
 }
 }
 */

/**
 * @author tjl
 * @version V1.0
 * @Title: MyInterceptor
 * @Package com.eureka.common
 * @Description: 拦截处理(用一句话描述该文件做什么)
 * @date 2018-3-28
 */
public class MyInterceptor implements HandlerInterceptor {

    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.printf("preHandle被调用");
        return true;    //如果false，停止流程，api被拦截
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }
    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
