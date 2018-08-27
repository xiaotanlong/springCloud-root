package com.example.ribbon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.ribbon.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description :Plase give some message
 * 修改记录：
 * 1 Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。
 * 它是一个基于HTTP和TCP的客户端负载均衡器。
 * 它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。
 */
@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;//设置随机访问策略

    @GetMapping("/consumer")
    public String dc() {
        //this.loadBalancerClient.choose("service-B");//设置随机访问策略
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}
