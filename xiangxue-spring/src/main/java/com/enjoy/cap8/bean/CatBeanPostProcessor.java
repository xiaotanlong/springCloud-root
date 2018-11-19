package com.enjoy.cap8.bean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 0217319
 * @version V1.0
 * @Description: 自定义后置处理器
 * BeanPostProcessor接口提供了两个供开发者自定义的方法：
        postProcessBeforeInitialization、postProcessAfterInitialization。
        postProcessBeforeInitialization：该方法主要针对spring在bean初始化时调用初始化方法前进行自定义处理。
        postProcessAfterInitialization：该方法主要针对spring在bean初始化时调用初始化方法后进行自定义处理。

 * spring容器在加载bean过程中有很多内置的后置处理器
 *          InstantiationAwareBeanPostProcessor：实例化Bean后置处理器（继承BeanPostProcessor）
 *          MergedBeanDefinitionPostProcessor：合并Bean定义后置处理器         （继承BeanPostProcessor）
 *          SmartInstantiationAwareBeanPostProcessor：智能实例化Bean后置处理器（继承InstantiationAwareBeanPostProcessor）
 *          DestructionAwareBeanPostProcessor：销毁Bean后置处理器（继承BeanPostProcessor）
 * 部分内置的BeanPostProcessor
 *          1、ApplicationContextAwareProcessor容器启动时会自动注册。
*                 注入那些实现ApplicationContextAware、MessageSourceAware、ResourceLoaderAware、EnvironmentAware、
                  EmbeddedValueResolverAware、ApplicationEventPublisherAware标识接口的Bean需要的相应实例，在postProcessBeforeInitialization回调方法中进行实施，
            2、BeanValidationPostProcessor默认不自动注册，Spring3.0开始支持。
                 提供对JSR-303验证规范支持。
                 根据afterInitialization是false/true决定调用postProcessBeforeInitialization或postProcessAfterInitialization来通过JSR-303规范验证Bean，默认false。
                当对象创建完,给bean赋值后,在WEB用得特别多;把页面提交的值进行校验
            3、InitDestroyAnnotationBeanPostProcessor此处理器用来处理@PostConstruct, @PreDestroy, 怎么知道这两注解是前后开始调用的呢,

 */
public class CatBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cat) {
            //输出原始属性
            Cat cat = (Cat) bean;
            cat.say();
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cat) {
            //修改属性值，并返回
            Cat cat = (Cat) bean;
            cat.setName("hello maomi");
            cat.setAge(3);
            return cat;
        }
        return bean;
    }

}

