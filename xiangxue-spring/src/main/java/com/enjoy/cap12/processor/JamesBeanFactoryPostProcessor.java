package com.enjoy.cap12.processor;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 扩展组件
 * BeanFactoryPostProcessor 针对  BeanFactory 的后置处理器
 *
 * Spring IoC容器允许BeanFactoryPostProcessor在容器实例化任何bean之前读取bean的定义(配置元数据)，并可以修改它。
 * 同时可以定义多个BeanFactoryPostProcessor，
 * 通过设置'order'属性来确定各个BeanFactoryPostProcessor执行顺序。
 *
 * BeanFactoryPostProcessor允许使用者修改容器中的bean definitions
 * 使用场景====在bean创建实例前 可以更改属性值
 *
 */
@Component
public class JamesBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("JamesBeanFactoryPostProcessor.....调到了BeanFactoryPostProcessor.postProcessBeanFactory()");
		//所有bean的定义，已经加载到beanFactory, 但是bean实例还没创建
		int count = beanFactory.getBeanDefinitionCount();
		String[] beanDefName = beanFactory.getBeanDefinitionNames();
		System.out.println("当前BeanFactory中有"+count+"个Bean");
		System.out.println(Arrays.asList(beanDefName));
		
	}

}
