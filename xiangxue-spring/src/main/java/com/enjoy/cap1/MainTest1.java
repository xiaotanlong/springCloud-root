package com.enjoy.cap1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 一、简单的用ApplicationContext做测试的话,获得Spring中定义的Bean实例(对象).可以用:
	 ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	 RegisterDAO registerDAO = (RegisterDAO)ac.getBean("RegisterDAO");
	 如果是两个以上:
	 ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","dao.xml"});
	 或者用通配符:
	 ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/*.xml");

 对于FileSystemXmlApplicationContext:
	 默认表示的是两种:

	 1.没有盘符的是项目工作路径,即项目的根目录;
	 2.有盘符表示的是文件绝对路径.
 */
public class MainTest1 {
	public static void main(String args[]){
		//把beans.xml的类加载到容器
		ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");

		//从容器中获取bean
		Person person = (Person) app.getBean("person");

		System.out.println(person);
	}
}
