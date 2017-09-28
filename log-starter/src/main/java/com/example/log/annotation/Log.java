package com.example.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName springcloudroot
 * @PackageName com.example.log.annotation
 * @Author tanjianglong
 * @CreatedTime 2017/9/21.
 * @Description :Plase give some message
 * 修改记录：1...;2....
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log { }
