package com.enjoy.cap8.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 0217319
 * @version V1.0
 * @Description: Spring容器会在创建该Bean之后，自动调用该Bean的setApplicationContextAware()方法，
 * 调用该方法时，会将容器本身作为参数传给该方法
 *          ——该方法中的实现部分将Spring传入的参数（容器本身）赋给该类对象的applicationContext实例变量，
 *          因此接下来可以通过该applicationContext实例变量来访问容器本身。
 *
 * 在Web应用中，Spring容器通常采用声明式方式配置产生：开发者只要在web.xml中配置一个Listener，
         该Listener将会负责初始化Spring容器，MVC框架可以直接调用Spring容器中的Bean，
         无需访问Spring容器本身。在这种情况下，容器中的Bean处于容器管理下，
         无需主动访问容器，只需接受容器的依赖注入即可。
    但在某些特殊的情况下，Bean需要实现某个功能，但该功能必须借助于Spring容器才能实现，
        此时就必须让该Bean先获取Spring容器，然后借助于Spring容器实现该功能。为了让Bean获取它所在的Spring容器，
        可以让该Bean实现ApplicationContextAware接口。
    下面示例为实现ApplicationContextAware的工具类，可以通过其它类引用它以操作spring容器及其中的Bean实例。


 */
//自定义后置处理器  获取spring ioc容器
public class ApplicationContextAwareAchieve implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;
    /**
     * 获取静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextAwareAchieve.applicationContext = applicationContext;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        /*if(applicationContext == null){
            throw new Exception("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
        }*/
    }
}
