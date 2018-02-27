package com.example.consumer.util;

import com.eureka.common.rediscluster.ComRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
//@Component("consumerRedisTemplate")
public class ConsumerRedisTemplate extends ComRedisTemplate {
    public static String Consumer_prefix  = "Consumer";
}
