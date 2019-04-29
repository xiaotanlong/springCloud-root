package com.tjl.spring.webflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * 功能描述: spring-webflow demo
 * @date: 2019/4/29
 */
@SpringBootApplication
@ImportResource(locations={"classpath:webmvc-config.xml","classpath:webflow-config.xml","classpath:shopping.xml"})
public class SpringWebflowApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringWebflowApplication.class, args);
    }

}
