package com.eureka.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @Author Hugo.Wwg
 * @Since 2017-06-16
 */
public class RedisKey implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(RedisKey.class);
    private static final long serialVersionUID = 1L;
    private RedisKeyPrefixEnum redisKeyPrefixEnum;
    private String key;

    public RedisKey(RedisKeyPrefixEnum redisKeyPrefixEnum, String key) {
        this.redisKeyPrefixEnum = redisKeyPrefixEnum;
        this.key = key;
    }

    private String makeRedisKeyString() {
        String newKey = redisKeyPrefixEnum.getKeyPrefix() + key;
        if (!StringUtils.isEmpty(newKey)) {
            try {
                newKey = new String(newKey.getBytes(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("RedisKey.getRedisKey 转码失败, 错误原因:{}", e);
            }
        }
        return newKey;
    }

    public String getRedisKey() {
        return makeRedisKeyString();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public RedisKeyPrefixEnum getRedisKeyPrefixEnum() {
        return redisKeyPrefixEnum;
    }

    public void setRedisKeyPrefixEnum(RedisKeyPrefixEnum redisKeyPrefixEnum) {
        this.redisKeyPrefixEnum = redisKeyPrefixEnum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
