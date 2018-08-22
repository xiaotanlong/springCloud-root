package com.example.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务容错保护（Hystrix服务降级）
 *      1.服务降级
 *      2.依赖隔离
 *      3.断路由
 *相关名词：
 *      1.DashBoard :仪表盘
 *      2.Hystrix ：
 *      3.CircuitBreaker：断路由
 *
 * 在应用主类中使用@EnableCircuitBreaker或@EnableHystrix注解开启Hystrix的使用
 *
 * 这里我们还可以使用Spring Cloud应用中的@SpringCloudApplication注解来修饰应用主类，
 *      该注解的具体定义如下所示。我们可以看到该注解中包含了上我们所引用的三个注解，
 *      这也意味着一个Spring Cloud标准应用应包含服务发现以及断路器。
 *
 * @HystrixCommand注解来指定服务降级方法
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerRibbonHystrixApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerRibbonHystrixApplication.class, args);
    }
}
