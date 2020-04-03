package com.tjl.spring.webflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: jianglong.Tan
 * @Date: 2019/4/29 16:44
 * @Description:
 */
@Controller
@RequestMapping("/web")
public class WebFlowController {

    @RequestMapping("/test1")
    public String test1(){
        return "test1";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "test2";
    }
}
