package com.example.xiangxuerocketmqc;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class XiangxueRocketmqCApplication {

	Logger log = LoggerFactory.getLogger(XiangxueRocketmqCApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(XiangxueRocketmqCApplication.class, args);
	}







}
