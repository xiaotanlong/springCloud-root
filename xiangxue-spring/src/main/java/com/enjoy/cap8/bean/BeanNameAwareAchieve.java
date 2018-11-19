package com.enjoy.cap8.bean;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author 0217319
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * 实现BeanNameAware接口需要实现setBeanName()方法，这个方法只是简单的返回我们当前的beanName
 */
public class BeanNameAwareAchieve implements BeanNameAware{
    @Override
    public void setBeanName(String s) {
        System.out.println("当前bean 名字  是  " +s);

    }
}
