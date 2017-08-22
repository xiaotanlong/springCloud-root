package com.example.client.Controller;


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
 * 修改记录：1...;2....
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(ComputeController.class);
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}
