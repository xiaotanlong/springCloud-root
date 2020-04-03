package com.example.xiangxuerocketmqc.consumer;

import com.example.xiangxuerocketmqc.OrderPaidEvent;
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
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "my-consumer_test-topic-2")
public class MyConsumer2 implements RocketMQListener<Object>{
    Logger log = LoggerFactory.getLogger(MyConsumer2.class);
    public void onMessage(Object orderPaidEvent) {
        log.info("==========test-topic-2====received orderPaidEvent: {}", orderPaidEvent);
    }
}
