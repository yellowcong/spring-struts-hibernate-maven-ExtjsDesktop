package com.yellowcong.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yellowcong.utils.JsonBuilder;

/**
 * 描述树形实体字段的注解
 * @author 狂飙のyellowcong
 * 2015年6月2日
 */

//运行时启动
@Retention(RetentionPolicy.RUNTIME)
//表示能够修饰的是字段 ，不能是method
@Target(ElementType.FIELD) 
public @interface FieldInfo {
	//字段名称 ,默认为空
	public String name() default ""; 
	//字段类型
	public String type() default ""; 
}
