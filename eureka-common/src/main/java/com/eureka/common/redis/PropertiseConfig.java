package com.eureka.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName springcloudroot
 * @PackageName com.eureka.common.redis
 * @Author tanjianglong
 * @CreatedTime 2017/8/23.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableCaching
public class PropertiseConfig {
    @Value("${spring.redis.hostName}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.maxActive}")
    private String maxActive;
    @Value("${spring.redis.maxWait}")
    private String maxWait;
    @Value("${spring.redis.maxIdle}")
    private String maxIdle;
    @Value("${spring.redis.minIdle}")
    private String minIdle;
    @Value("${spring.redis.timeout}")
    private String timeout;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }
}
