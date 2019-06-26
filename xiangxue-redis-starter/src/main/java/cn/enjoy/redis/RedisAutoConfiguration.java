
package cn.enjoy.redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
这些是springboot特有的，常见的条件依赖注解有：
    @ConditionalOnBean，仅在当前上下文中存在某个bean时，才会实例化这个Bean。

    @ConditionalOnClass，某个class位于类路径上，才会实例化这个Bean。

    @ConditionalOnExpression，当表达式为true的时候，才会实例化这个Bean。

    @ConditionalOnMissingBean，仅在当前上下文中不存在某个bean时，才会实例化这个Bean。

    @ConditionalOnMissingClass，某个class在类路径上不存在的时候，才会实例化这个Bean。

    @ConditionalOnNotWebApplication，不是web应用时才会实例化这个Bean。

    @AutoConfigureAfter，在某个bean完成自动配置后实例化这个bean。

    @AutoConfigureBefore，在某个bean完成自动配置前实例化这个bean。
 */
@Configuration //开启配置
@ConditionalOnClass(Jedis.class)
@EnableConfigurationProperties(RedisProperties.class) //开启使用映射实体对象
@ConditionalOnProperty//存在对应配置信息时初始化该配置类
        (
                prefix = "redis",//存在配置前缀redis
                value = "enabled",//开启
                matchIfMissing = true//缺失检查
        )
public class RedisAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public Jedis jedis(RedisProperties redisProperties){
        return new Jedis(redisProperties.getHost(), redisProperties.getPort());
    }
}