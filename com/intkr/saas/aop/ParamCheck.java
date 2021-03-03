package com.intkr.saas.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * http请求参数检查
 * 
 * @author Beiden
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParamCheck {

	// schema
	public String schema() default "";

	// method
	public String method() default "get/post";

}
