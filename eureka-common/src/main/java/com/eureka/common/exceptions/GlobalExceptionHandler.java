package com.eureka.common.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.Serializable;

/**
 * @author 0217319
 * @version V1.0
 * @Description: (用一句话描述该文件做什么)
 * @date
 */
//@ControllerAdvice + @ExceptionHandler 全局处理 Controller 层异常
/**
 * 一、优缺点
 优点：将 Controller 层的异常和数据校验的异常进行统一处理，减少模板代码，减少编码量，提升扩展性和可维护性。
 缺点：只能处理 Controller 层未捕获（往外抛）的异常，对于 Interceptor（拦截器）层的异常，Spring 框架层的异常，就无能为力了。
 */
@ControllerAdvice
public class GlobalExceptionHandler implements Serializable {
}
