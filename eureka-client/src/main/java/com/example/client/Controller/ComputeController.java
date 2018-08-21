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
 * 修改记录：
 * 1 可以看到这里，我们注入了LoadBalancerClient和RestTemplate，并在/consumer接口的实现中，
 * 先通过loadBalancerClient的choose函数来负载均衡的选出一个eureka-client的服务实例，
 * 这个服务实例的基本信息存储在ServiceInstance中，然后通过这些对象中的信息拼接出访问/dc接口的详细地址，
 * 最后再利用RestTemplate对象实现对服务提供者接口的调用。
 */
@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(ComputeController.class);
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    SimpleService simpleService;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();//获取当前注册的服务
        System.out.println(services);
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
