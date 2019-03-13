package com.example.xiangxuerocketmqc.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/3/12 16:24
 *
 * 消息 防止重复消费
 * 幂等：
 */
@Service
@RocketMQMessageListener(
        topic = "test-topic",
        consumerGroup = "test"
)
public class MyConsumerTran implements RocketMQListener<String> {
    Logger log = LoggerFactory.getLogger(MyConsumerTran.class);
    public void onMessage(String message) {
        log.info(" =======MyConsumerTran=======received message: {}", message);
    }
}
