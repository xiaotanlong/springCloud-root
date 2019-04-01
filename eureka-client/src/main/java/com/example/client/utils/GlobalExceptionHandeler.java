package com.example.client.utils;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 异常捕获类(用一句话描述该文件做什么)
 * @date 2019/3/29 14:06
 */
@ControllerAdvice
public class GlobalExceptionHandeler {

    //可以定义自己的业务异常  继承 RuntimeException
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultExceptionHandeler(HttpServletRequest reg , Exception e){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0003);
        map.put("msg", e.getMessage());
        return map;
    }

    /**
     *Spring Boot 2.0 内嵌 Tomcat 定制 ： WebServerFactoryCustomizer
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return (factory -> {
            ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/404.do");
            factory.addErrorPages(errorPage);
        });
    }
}
