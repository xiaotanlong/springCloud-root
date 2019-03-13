package com.example.rocketmq.rocketmq.service;

import com.example.rocketmq.rocketmq.bean.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xiaotantjl@163.com
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date 2019/3/12 16:19
 *
 *
 * txProducerGroup   发送的事务消息分组   与 @RocketMQTransactionListener(txProducerGroup ="test")  对应
 *
 * destination  目的地   就是topic
 */
@Service
public class SendDemo implements InitializingBean{
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    Logger log = LoggerFactory.getLogger(SendDemo.class);
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("SendDemo----send{}","test-topic-1");
        run();
    }

    public void run(String... args) throws Exception {
        //rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
//        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
        Message msg = MessageBuilder.withPayload("Hello, World!  asdsadsadas  dsda Transaction ").build();
        rocketMQTemplate.sendMessageInTransaction("test", "test-topic" ,msg, null);
    }
}
