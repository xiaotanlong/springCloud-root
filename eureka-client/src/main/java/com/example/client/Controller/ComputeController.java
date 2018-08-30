package com.example.client.Controller;


import com.example.client.Service.SimpleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.demo1.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/10.
 * @Description :Plase give some message
 *
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(ComputeController.class);
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    SimpleService simpleService;

    @GetMapping("/dc")
    public String dc() throws InterruptedException{
        String services = "Services: " + discoveryClient.getServices();//获取当前注册的服务
        System.out.println(services);
        Thread.sleep(2000L);
        return services;
    }

    @GetMapping("/test")
    public String test() {
        simpleService.test(111);
        return "ooo";
    }
    @GetMapping("/work")
    public String work() {
        simpleService.work(000);
        return "ooo";
    }
    @GetMapping("/core")
    public String core() {
        simpleService.core(222);
        return "ooo";
    }
}
