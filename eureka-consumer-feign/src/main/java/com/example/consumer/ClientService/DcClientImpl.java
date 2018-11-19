package com.example.consumer.ClientService;

import org.springframework.stereotype.Component;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: 提供熔断的降级服务
 * @date
 */
@Component
public class DcClientImpl implements DcClient {
    @Override
    public String consumer() {
        return "发生 error ----- 我是降级服务选择~";
    }
}
