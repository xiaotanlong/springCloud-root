package com.example.consumer.com.servlet3.test;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description:  ServletContainerInitializer  的spring-mvc实现的测试
 * @date 2019/3/6 15:08
 */
public class TjlDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("==========" + servletContext.getContextPath());
    }
}
