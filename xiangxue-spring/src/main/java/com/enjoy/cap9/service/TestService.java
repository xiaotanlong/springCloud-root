package com.enjoy.cap9.service;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enjoy.cap9.dao.TestDao;

/**
 * @Resource装配顺序：
	①如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
	②如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
	③如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
	④如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。
	 @Resource的作用相当于@Autowired，只不过@Autowired按照byType自动注入。


 @Inject自动装配的使用:
 注:@Inject与@Autowired的区别如下:
 @Inject和Autowired一样可以装配bean, 并支持@Primary功能, 可用于非spring框架.
 @Inject缺点: 但不能支持@Autowired(required = false)的功能,需要引入第三方包javax.inject


 */
@Service
public class TestService {
	//@Qualifier("testDao")
	@Autowired
	//效果是一样的,与Autowired一样可以装配bean
	//1,不支持Primary功能
	//2,不支持Autowired false
	//@Resource(name="testDao")
	//@Inject
	private TestDao testDao;

	public void println(){
		System.out.println(testDao);
	}
}
