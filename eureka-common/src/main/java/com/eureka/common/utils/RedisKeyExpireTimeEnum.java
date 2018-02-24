package com.eureka.common.utils;

/**
 * @Author Hugo.Wwg
 * @Since 2017-06-16
 */
public enum RedisKeyExpireTimeEnum {

    SECONDS_OF_ONE_MINUTE(60),
    SECONDS_OF_THREE_MINUTE(60 * 3),
    SECONDS_OF_TEN_MINUTE(60 * 10),
    SECONDS_OF_HALF_HOUR(60 * 30),
    SECONDS_OF_ONE_DAY(60 * 60 * 24),
    SECONDS_OF_THREE_DAY(60 * 60 * 24 * 3),
    SECONDS_OF_ONE_WEEK(60 * 60 * 24 * 7);

    private Integer expireSeconds;

    public Integer getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    RedisKeyExpireTimeEnum(Integer expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
