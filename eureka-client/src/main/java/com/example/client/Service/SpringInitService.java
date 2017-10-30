package com.example.client.Service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.client.Service
 * @Author tanjianglong
 * @CreatedTime 2017/10/30.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Component
@Order(value=1)
public class SpringInitService implements CommandLineRunner {

    public void run(String... arg0) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        System.out.println(">>>>>>>>>>>>>>>222222222222222222222222222222<<<<<<<<<<<<<");
    }
}
