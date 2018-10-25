package com.enjoy.cap6.config;

import org.springframework.beans.factory.FactoryBean;

import com.enjoy.cap6.bean.Monkey;

/**
 * FactoryBean  是往容器里注册bean
 */
public class JamesFactoryBean implements FactoryBean<Monkey>{

	@Override
	public Monkey getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Monkey();
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Monkey.class;
	}
	
	@Override
	public boolean isSingleton() {
		return false;
	}
}
