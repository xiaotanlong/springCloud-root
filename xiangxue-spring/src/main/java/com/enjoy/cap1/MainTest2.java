package com.enjoy.cap1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.enjoy.cap1.config.MainConfig;

/**
 * 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。
 * 			避免使用application.xml进行配置。相比XML配置，更加便捷。
 * 构造方法：
 * 			1.无参   先获取上下文容器，在往容器中加载bean
 * 		    2.Class... annotatedClasses  直接加载一个带有@Configuration注解声明的配置类
 * 		    3.String... basePackages 加载包路径下的带有spring注解的类
 * 		    4.DefaultListableBeanFactory beanFactory  加入一个beanFactory
 *
 *  自己手动加载时步骤：
 *  		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
			 ctx.register(AppConfig.class);
			 ctx.refresh();
 */
public class MainTest2 {
	public static void main(String args[]){
		//把beans.xml的类加载到容器
		//ApplicationContext app = new ClassPathXmlApplicationContext("beans.xml");
		//Annotation 注解的意思
		ApplicationContext app = new AnnotationConfigApplicationContext(MainConfig.class);

		//从容器中获取bean
		//Person person = (Person) app.getBean("person01");

		//System.out.println(person);

		String[] namesForBean = app.getBeanNamesForType(Person.class);
		for(String name:namesForBean){
			System.out.println(name);
		}


	}
}
