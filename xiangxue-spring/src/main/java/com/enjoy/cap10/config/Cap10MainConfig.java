package com.enjoy.cap10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.enjoy.cap10.aop.Calculator;
import com.enjoy.cap10.aop.LogAspects;

/**
 * 日志切面类的方法需要动态感知到div()方法运行, 
 *  通知方法:
 *     前置通知:logStart(); 在我们执行div()除法之前运行(@Before)
 *     后置通知:logEnd();在我们目标方法div运行结束之后 ,不管有没有异常(@After)
 *     返回通知:logReturn();在我们的目标方法div正常返回值后运行(@AfterReturning)
 *     异常通知:logException();在我们的目标方法div出现异常后运行(@AfterThrowing)
 *     环绕通知:动态代理, 需要手动执行joinPoint.procced()(其实就是执行我们的目标方法div,), 执行之前div()相当于前置通知, 执行之后就相当于我们后置通知(@Around)
 *
 *
 *  需要开启基于注解的AOP模式
		 给配置类中加@EnableAspectJAutoProxy[一定得加上,关键]
		 注意: 在spring以后会有很多@EnableXXXX, 表示开启某项功能, 取代XML配置

 AOP看起来很麻烦, 只要3步就可以了:
 1,将业务逻辑组件和切面类都加入到容器中, 告诉spring哪个是切面类(@Aspect)
 2,在切面类上的每个通知方法上标注通知注解, 告诉Spring何时运行(写好切入点表达式,参照官方文档)
 3,开启基于注解的AOP模式  @EableXXXX


 <!--启用aop自动代理类配置-->  使用xml配置文件
 <aop:aspectj-autoproxy proxy-target-class="true" />
 用户可以自己控制代理的类型，通过proxyTargetClass属性，默认false，采用JDK动态代理织入增强；
 如果设为true，则采用CGLIB动态代理织入增强。
 不过即使proxyTargetClass设置为false，如果目标类没有声明接口，则Spring将自动使用CGLib动态代理。


 */
@Configuration
//开启AspectJ 自动代理模式,如果不填proxyTargetClass=true，默认为false，
@EnableAspectJAutoProxy//开启aop
public class Cap10MainConfig {
	@Bean
	public Calculator calculator(){
		return new Calculator();
	}

	@Bean
	public LogAspects logAspects(){
		return new LogAspects();
	}
}
