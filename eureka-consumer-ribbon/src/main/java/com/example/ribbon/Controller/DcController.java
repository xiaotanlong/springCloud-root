package com.example.ribbon.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.ribbon.Controller
 * @Author tanjianglong
 * @CreatedTime 2017/8/22.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@RestController
public class DcController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }
}
