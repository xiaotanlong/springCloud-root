package com.enjoy.cap7.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Spring中的后置处理器BeanPostProcessor讲解
 *
 * BeanPostProcessor接口作用：
 * 		如果我们想在Spring容器中完成bean实例化、配置以及其他初始化方法前后要添加一些自己逻辑处理。
 		我们需要定义一个或多个BeanPostProcessor接口实现类，然后注册到Spring IoC容器中。
 		每一个bean加载到ioc都会有这个后置处理器的触发---
 *当容器关闭时对单实例的bean进行销毁.  多实例bean spring容器不处理
 */
@Component
public class JamesBeanPostProcessor implements BeanPostProcessor,Ordered{
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		//返回一个的对象(传过来的对象)
		//在初始化方法调用之前进行后置处理工作,
		//什么时候调用它: init-method=init之前调用
		System.out.println("JamesBeanPostProcessor1===============postProcessBeforeInitialization...."+beanName+"..."+bean);
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("JamesBeanPostProcessor1===============postProcessAfterInitialization...."+beanName+"..."+bean);
		return bean;
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
