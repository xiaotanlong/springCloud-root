package com.eureka.common.rediscluster;
import com.eureka.common.serialize.Serializer;
import com.eureka.common.serialize.SerializerFactory;
import com.eureka.common.utils.RedisKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryJedisCluster;

/**
 * @author
 * @version V1.0
 * @Title:
 * @Package
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
@Component
public class ComRedisTemplate {
    private static final Logger LOGGER    = LoggerFactory.getLogger(ComRedisTemplate.class);
    @Autowired
    protected BinaryJedisCluster jedisCluster;
    @Autowired
    protected RedisProperties redisProperties;

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    private Serializer serializer = SerializerFactory.getSerializer();


    /**
     * 设置永不过期
     *
     * @param redisKey 缓存的key对象
     * @param value    缓存value
     */
    public void set(RedisKey redisKey, Object value) {
        jedisCluster.set(redisKey.getRedisKey().getBytes(), serializer.encode(value));
        LOGGER.debug("RedisUtil:set cache key={},value={}", redisKey.getRedisKey(), value);
    }


}
