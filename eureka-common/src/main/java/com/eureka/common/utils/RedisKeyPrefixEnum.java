package com.eureka.common.utils;


public enum RedisKeyPrefixEnum {

    PAY_KEY("pay:", "pay 模块 redis key 前缀"),
    USER_KEY("user:", "user 模块 redis key 前缀"),
    TASK_KEY("task:", "task 模块 redis key 前缀"),
    SERVICE_KEY("service:", "service 模块 redis key 前缀");

    private String keyPrefix;
    private String keyInfo;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String getKeyInfo() {
        return keyInfo;
    }

    public void setKeyInfo(String keyInfo) {
        this.keyInfo = keyInfo;
    }

    RedisKeyPrefixEnum(String keyPrefix, String keyInfo) {
        this.keyPrefix = keyPrefix;
        this.keyInfo = keyInfo;
    }
}
