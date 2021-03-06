package com.example.consumer.Controller;

import com.example.consumer.ClientService.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.consumer.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description : eureka-consumer-feign  demo
 * 修改记录：
 * 1:直接调用  http
 * 2：提供熔断 降级服务
 */
@RestController
public class DcController {
    @Autowired
    private DcClient dcClient;


    @GetMapping("/consumer")
    public String dc() {
        return dcClient.consumer();
    }
}
