package com.enjoy.cap7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.enjoy.cap1.Person;
import com.enjoy.cap7.bean.Bike;

/**
 * @Bean(initMethod="init", destroyMethod="destory")
 * 方式 执行 bean的创建 和销毁方法
 *
 * 多实例: 容器只负责初始化,但不会管理bean, 容器关闭不会调用销毁方法
 */
@ComponentScan("com.enjoy.cap7.bean")
@Configuration
public class Cap7MainConfigOfLifeCycle {
	@Bean("person")
	public Person person(){
		System.out.println("给容器中添加person.......");
		return new Person("person",20);
	}

	/**
	 * Bike声明为多实例prototype 时  只有在获取bean时才会创建bean的实例
	 * @return
	 */
	@Bean(initMethod="init", destroyMethod="destory")
	public Bike bike(){
		return new Bike();
	}

}
