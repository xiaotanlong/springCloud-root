package com.enjoy.cap6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.enjoy.cap1.Person;
import com.enjoy.cap6.bean.Cat;
import com.enjoy.cap6.bean.Dog;

@Configuration
@Import(value = { Dog.class,Cat.class, JamesImportSelector.class,JamesImportBeanDefinitionRegistrar.class })
public class Cap6MainConfig {
	/**
	 * 给容器中注册组件的方式
	 * 1,@Bean: [导入第三方的类或包的组件],比如Person为第三方的类, 需要在我们的IOC容器中使用
	 * 2,包扫描+组件的标注注解(@ComponentScan:  @Controller, @Service  @Reponsitory  @ Componet),一般是针对 我们自己写的类,使用这个
	 * 3,@Import:[快速给容器导入一个组件] 注意:@Bean有点简单
	 *      a,@Import(要导入到容器中的组件):容器会自动注册这个组件,bean 的 id为全类名  类似 xml不给id
	 *      b,ImportSelector:是一个接口,返回需要导入到容器的组件的全类名数组
	 *      c,ImportBeanDefinitionRegistrar:可以手动添加组件到IOC容器, 所有Bean的注册可以使用BeanDifinitionRegistry
	 *          写JamesImportBeanDefinitionRegistrar实现ImportBeanDefinitionRegistrar接口即可   # Definition Registrar定义注册员
	 *  4,使用Spring提供的FactoryBean(工厂bean)进行注册
	 *
	 *
	 *  这个可以加载资源文件  不带注解的类
	 *
	 *
	 *  ImportBeanDefinitionRegistrar 定义注册员 接口工具   向IOC中写入    spring 启动时写入bean  可以用在@Import
	 *  ImportSelector  导入选择者  接口  工具  用在@Import
	 *
	 *
	 */
	//容器启动时初始化person的bean实例
	@Bean("person")
	public Person person(){
		return new Person("james",20);
	}
	@Bean
	public JamesFactoryBean jamesFactoryBean(){
		return new JamesFactoryBean();
	}

	/**
	 * 二、比较@Resource、@Autowired
	 @Resource
	 @Resource默认按byName自动注入。
	 既不指定name属性，也不指定type属性，则自动按byName方式进行查找。如果没有找到符合的bean，
	 则回退为一个原始类型进行进行查找，如果找到就注入。
	 只是指定了@Resource注解的name，则按name后的名字去bean元素里查找有与之相等的name属性的bean。
	 只指定@Resource注解的type属性，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常。
	 @Autowired

	 @Autowired默认先按byType进行匹配，如果发现找到多个bean，则又按照byName方式进行匹配，如果还有多个，则报出异常。
	 ---------------------
	 */
}
