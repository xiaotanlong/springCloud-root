package com.example.consumer.ClientService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.consumer.interfaces
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@FeignClient("EUREKA-CLIENT")
public interface DcClient {
    @GetMapping("/dc")
    String consumer();
}
