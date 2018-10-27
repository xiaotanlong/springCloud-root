import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.enjoy.cap5.config.Cap5MainConfig;
import com.enjoy.cap6.config.Cap6MainConfig;

public class Cap6Test {
	@Test
	public void test01(){
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Cap6MainConfig.class);

		System.out.println("IOC容器创建完成........");


		//加上&开头  spring的加载时会认为这是一个 FactoryBean  这时会直接返回这个bean的对象
		//不会加载实现接口方法返回的bean
		Object bean1 = app.getBean("&jamesFactoryBean");
		Object bean2 = app.getBean("jamesFactoryBean");//取Money
		System.out.println("bean的类型="+bean1.getClass());
		System.out.println(bean1 == bean2);

		// TODO 遗留问题  这里应该打印出 Monkey 这个类注册在了spring容器中  ，但是没有打印出来
		String[] beanDefinitionNames = app.getBeanDefinitionNames();
		for(String name:beanDefinitionNames){
			System.out.println("bean name is : " + name);
		}





	}
}
