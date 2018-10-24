package com.enjoy.cap2.config;

/**
 *
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import com.enjoy.cap1.Person;
import com.enjoy.cap2.controller.OrderController;
import org.springframework.stereotype.Service;


/**@ComponentScan
 * 		指定扫描范围；
 * 		扫描过滤器；
 * 		自定义过滤规则 (FilterType)中的几种规则--ANNOTATION:注解
 * 						ASSIGNABLE_TYPE:给定的类型  classs 中写出具体要扫描的类 例如{OrderController.class,**.class}
 * 						ASPECTJ:类似表达式  REGEX:正则
 * 						CUSTOM:自定义  自已写类，实现TypeFilter接口
 * 		useDefaultFilters=false   配置 不使用 默认过滤
 * @Filter  includeFilters和excludeFilters   配置过滤规则
 * 			type：过滤类型
 * 			classes：过滤的类型   可以是自定义
 *
 * 	问题：(翻车)这样配置：includeFilters={ @Filter(type=FilterType.ANNOTATION, classes={Controller.class})}
 * 			includeFilters和excludeFilters   属性时   useDefaultFilters设置影响获取的规则
 * 	        excludeFilters Controller 时 useDefaultFilters=true  显示正确
 * 	        excludeFilters Controller 时 useDefaultFilters=false  显示错误  ？问题还是没解决
 * 	        includeFilters Controller 时 useDefaultFilters=false  显示正确
 * 	        includeFilters Controller 时 useDefaultFilters=true  显示错误
 * 	 另外配置自定义：includeFilters={ @Filter(type=FilterType.CUSTOM, classes={Controller.class})}
 *
 *   原因：跟到底层源码分析--如果useDefaultFilters=true
 *   		@Component注解的类都会加载   @Controller @Service @Repository  类似继承 @Component
 *   	                                   @Bean 没有继承
 *
 *
 *
 * 	 另外 @Configuration 注解的类  和这个类中的 @bean  一定会被扫描加载
 */
@Configuration//相当于application-bean.xml
@ComponentScan(value="com.enjoy.cap2", includeFilters ={
		@Filter(type=FilterType.CUSTOM, classes={JamesTypeFilter.class})
}, useDefaultFilters=false)
//多个 @ComponentScan  取并集
@ComponentScan(value="com.enjoy.cap2", includeFilters ={
		@Filter(type=FilterType.ANNOTATION, classes={Service.class})
}, useDefaultFilters=false)
public class Cap2MainConfig {
	//给容器中注册一个bean, 类型为返回值的类型,
	@Bean
	public Person person01(){
		return new Person("james",20);
	}
}
