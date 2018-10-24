package com.enjoy.cap6.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 返回空会抛异常    因为源码里面直接使用了改参数.length
 * 用这个方法可以导入 当前项目外部的  没有注解的类（例如   servive上忘了加了注解    可以用@Import）
 */
public class JamesImportSelector implements ImportSelector{
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata){
		//返回全类名的bean
		return new String[]{"com.enjoy.cap6.bean.Fish","com.enjoy.cap6.bean.Tiger"};
	}
}
