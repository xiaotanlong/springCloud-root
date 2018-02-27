package com.example.consumer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

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
    //@Autowired
    //private ComRedisTemplate comRedisTemplate;
    //最简单的连接使用 不需要config.java
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/redisTest")
    public String redisTest() {
        //comRedisTemplate.set(new RedisKey(RedisKeyPrefixEnum.PAY_KEY,"111"),"test====");
        //String s = redisTemplate.get(ConsumerRedisTemplate.Consumer_prefix,"111");
        List<String> list =new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("v");
        stringRedisTemplate.opsForValue().set("abc", "测试");
        stringRedisTemplate.opsForList().leftPushAll("qq",list); // 向redis存入List

        return "111111";
    }

    @Autowired
    private JedisCluster jedisCluster;
    @GetMapping("/jedisClusterTest")
    public String jedisClusterTest() {
        String s = jedisCluster.set("test_jedis_cluster", "38967");
        //Assert.("38967", jedisCluster.get("test_jedis_cluster"));
        //jedisCluster.del("test_jedis_cluster");

        return "111111" + s;
    }
}
