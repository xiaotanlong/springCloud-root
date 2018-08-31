package com.example.consumer.ClientService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.consumer.interfaces
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description :测试feign调用接口
 * 修改记录：
 * 1：eureka-client 代表的是提供服务的id
 * 2：interface  是服务调用一个其他服务的工具类
 * 3:fallback  是使用熔断器的配置，提供一个发生熔断时的降级服务
 */
@FeignClient(value = "eureka-client",fallback = DcClientImpl.class )
public interface DcClient {

    @GetMapping("/dc")
    public String consumer();
}
