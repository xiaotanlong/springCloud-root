package com.enjoy.cap7.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * 可以使用JSR250规则定义的(java规范)两个注解来实现
	 @PostConstruct: 在Bean创建完成,且属于赋值完成后进行初始化,属于JDK规范的注解
	 @PreDestroy: 在bean将被移除之前进行通知, 在容器销毁之前进行清理工作
	 提示: JSR是由JDK提供的一组规范
 */
@Component
public class Jeep {
	public Jeep(){
		System.out.println("Jeep.....constructor........");
	}
	@PostConstruct
	public void init(){
		System.out.println("Jeep.....@PostConstruct........");
	}
	
	@PreDestroy
	public void destory(){
		System.out.println("Jeep.....@PreDestroy......");
	}
}
