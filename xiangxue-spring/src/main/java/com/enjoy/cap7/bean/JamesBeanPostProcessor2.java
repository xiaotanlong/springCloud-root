package com.enjoy.cap7.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author 0217319
 * @version V1.0
 * @Description: spring对多个后置处理器
 * @date 2018/11/19 9:57
 */
@Component
public class JamesBeanPostProcessor2 implements BeanPostProcessor ,Ordered{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("JamesBeanPostProcessor2===============postProcessBeforeInitialization...."+beanName+"..."+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("JamesBeanPostProcessor2===============postProcessAfterInitialization...."+beanName+"..."+bean);
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
