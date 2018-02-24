package com.eureka.common.rediscluster;

import com.google.common.collect.Sets;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
@Configuration
public class RedisClusterConfig {
    private static final Logger log = LoggerFactory.getLogger(RedisClusterConfig.class);
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public BinaryJedisCluster getJedisCluster() {
        String[] serverArray = new String[]{};
        try {
            serverArray = redisProperties.getClusterNodes().split(",");
        } catch (Exception e) {
            log.error("redisSentinel.cache.clusterNodes 无法解析");
        }

        Set<HostAndPort> nodes = Sets.newHashSet();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMinIdle(redisProperties.getMinIdle());
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMaxTotal(redisProperties.getMaxActive());
        config.setMaxIdle(redisProperties.getMaxIdle());
        BinaryJedisCluster binaryJedisCluster = new BinaryJedisCluster(nodes,
                redisProperties.getCommandTimeout(),
                redisProperties.getMaxAttempts(),
                config);
        return binaryJedisCluster;
    }
}
