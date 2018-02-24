package com.example.consumer.Controller;

import com.eureka.common.utils.RedisKey;
import com.eureka.common.utils.RedisKeyPrefixEnum;
import com.example.consumer.util.ConsumerRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.consumer.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/11.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@RestController
public class DcController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ConsumerRedisTemplate redisTemplate;

    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/redisTest")
    public String redisTest() {
        redisTemplate.set(new RedisKey(RedisKeyPrefixEnum.PAY_KEY,"111"),"test====");
        //String s = redisTemplate.get(ConsumerRedisTemplate.Consumer_prefix,"111");
        return "111111";
    }
}
