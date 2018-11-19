package com.enjoy.cap8.config;

import com.enjoy.cap8.bean.BeanNameAwareAchieve;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.enjoy.cap8.bean.Bird;

@Configuration
@PropertySource(value="classpath:/test.properties")
@ComponentScan(value="com.enjoy.cap8.bean")
public class Cap8MainConfig {
	@Bean
	public Bird bird(){
		return new Bird();
	}
	@Bean
	public BeanNameAwareAchieve beanNameAwareAchieve(){
		return new BeanNameAwareAchieve();
	}
}
