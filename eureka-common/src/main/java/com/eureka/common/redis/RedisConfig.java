package com.eureka.common.redis;

import com.google.common.collect.Sets;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;

import java.util.Set;

/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.redis
 * @Author tanjianglong
 * @CreatedTime 2017/8/23.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Configuration
public class RedisConfig {
    @Autowired
    private PropertiseConfig propertiseConfig;

    /*@Bean
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置最大实例总数
        jedisPoolConfig.setMaxTotal(150);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMinIdle(10);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        jedisPoolConfig.setMaxWaitMillis(3 * 1000);
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(true);
        // 在还会给pool时，是否提前进行validate操作
        jedisPoolConfig.setTestOnReturn(true);
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(500);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1000);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(1000);
        jedisPoolConfig.setNumTestsPerEvictionRun(100);
        return jedisPoolConfig;
    }*/

    @Bean
    public BinaryJedisCluster getJedisCluster() {
        String[] serverArray = new String[]{};
        try {
            serverArray = propertiseConfig.getHostName().split(",");
        } catch (Exception e) {

        }

        Set<HostAndPort> nodes = Sets.newHashSet();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(30);
        config.setMaxIdle(10);
        config.setMaxTotal(150);
        BinaryJedisCluster binaryJedisCluster = new BinaryJedisCluster(nodes,
                3000,
                5,
                config);
        return binaryJedisCluster;
    }

}
