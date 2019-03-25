package com.example.rocketmq.rocketmq.controller;

import com.example.rocketmq.rocketmq.bean.CountDownTest;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 测试使用的 controller
 * @date 2019/3/15 9:53
 */

@Controller
@RequestMapping("/test")
public class TestController {
    Logger log = LoggerFactory.getLogger(TestController.class);
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @RequestMapping(value = "/send")
    @ResponseBody
    public String testSend() throws Exception{
        run();
        return "ok";
    }

    public void run(String... args) throws Exception {
        //rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
//        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
        CountDownTest<String> count = new CountDownTest(1);
        Message msg = MessageBuilder.withPayload("Hello, World!  SendDemo 2 Transaction ").build();
        CountDownTest.putObj(msg.hashCode()+"",count);
        rocketMQTemplate.sendMessageInTransaction("test", "test-topic" ,msg, msg.hashCode()+"");
        count.await(5000, TimeUnit.MILLISECONDS);

        log.info("end -----------------{}",count.getResurt());

    }
}
