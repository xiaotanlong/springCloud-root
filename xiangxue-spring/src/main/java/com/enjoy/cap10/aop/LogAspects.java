package com.enjoy.cap10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Aspect(切面): 通常是一个类，里面可以定义切入点和通知
	JointPoint(连接点):  程序执行过程中明确的点，一般是方法的调用
	Advice(通知):  AOP在特定的切入点上执行的增强处理:
		 @Before:  标识一个前置增强方法，相当于BeforeAdvice的功能
		 @After:  final增强，不管是抛出异常或者正常退出都会执行。
		 @AfterReturning:  后置增强，似于AfterReturningAdvice, 方法正常退出时执行
		 @AfterThrowing:  异常抛出增强，相当于ThrowsAdvice
		 @Around: 环绕增强，相当于MethodInterceptor
 	Pointcut(切入点):   带有通知的连接点，在程序中主要体现为书写切入点表达式
 	AOP Proxy：AOP框架创建的对象，代理就是目标对象的加强。Spring中的AOP代理可以使JDK动态代理，也可以是CGLIB代理，前者基于接口，后者基于子类。
 */
//日志切面类
@Aspect
public class LogAspects {
	@Pointcut("execution(public int com.enjoy.cap10.aop.Calculator.*(..))")
	public void pointCut(){};
	
	//@before代表在目标方法执行前切入, 并指定在哪个方法前切入
	@Before("pointCut()")
	public void logStart(){
		System.out.println("除法运行....参数列表是:{}");
	}
	@After("pointCut()")
	public void logEnd(){
		System.out.println("除法结束......");
		
	}
	@AfterReturning("pointCut()")
	public void logReturn(){
		System.out.println("除法正常返回......运行结果是:{}");
	}
	@AfterThrowing("pointCut()")
	public void logException(){
		System.out.println("运行异常......异常信息是:{}");
	}

	//环绕通知
	@Around("pointCut()")
	public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		System.out.println("@Arount:执行目标方法之前...");
		Object obj = proceedingJoinPoint.proceed();//相当于开始调div地
		System.out.println("@Arount:执行目标方法之后...");
		return obj;
	}
}
