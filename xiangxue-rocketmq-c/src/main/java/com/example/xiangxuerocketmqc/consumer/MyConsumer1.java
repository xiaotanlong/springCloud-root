package com.example.xiangxuerocketmqc.consumer;

import com.example.xiangxuerocketmqc.XiangxueRocketmqCApplication;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/3/12 16:24
 */
@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer1 implements RocketMQListener<String> {

    Logger log = LoggerFactory.getLogger(MyConsumer1.class);
    public void onMessage(String message) {
        log.info("===========MyConsumer1========received message: {}", message);
    }
}
