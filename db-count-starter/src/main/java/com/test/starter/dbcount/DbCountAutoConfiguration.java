package com.test.starter.dbcount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * @ProjectName springcloudroot
 * @PackageName com.test.starter.dbcount
 * @Author tanjianglong
 * @CreatedTime 2017/9/21.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Configuration
public class DbCountAutoConfiguration {
    @Autowired
    private HealthAggregator healthAggregator;
    /*@Bean
    public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
        return new DbCountRunner(repositories);
    }*/

    @Bean
    public HealthIndicator dbCountHealthIndicator(Collection<CrudRepository> repositories) {
        CompositeHealthIndicator compositeHealthIndicator = new
                CompositeHealthIndicator(healthAggregator);
        for (CrudRepository repository: repositories) {
            String name = DbCountRunner.getRepositoryName(repository.getClass());
            compositeHealthIndicator.addHealthIndicator(name, new DbCountHealthIndicator(repository));
        }
        return compositeHealthIndicator;
    }
}
