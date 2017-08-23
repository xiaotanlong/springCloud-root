package com.eureka.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.BinaryJedisCluster;

/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.redis
 * @Author tanjianglong
 * @CreatedTime 2017/8/23.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
public class RedisTemplate {

    @Autowired
    protected BinaryJedisCluster jedisCluster;

    /**
     * 设置永不过期
     *
     * @param redisKey 缓存的key对象
     * @param value    缓存value
     */
    public void set(String redisKey, Object value) {
        //redisConfig.set(redisKey, value);
    }
}
