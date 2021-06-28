package com.ntc.paas.plat.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller层-注解切面
 *
 * @author allen.yuan
 * @date 2021年6月23日 上午10:06:40
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SystemLog {

	/**
	 * 操作描述 业务名称business
	 *
	 * @return
	 */
	String desc() default "";

	/**
	 * 操作类型 create,modify,delete,query
	 *
	 * @return
	 */
	OperateType opType();

	/**
	 * 是否打印请求参数
	 *
	 * @return
	 */
	boolean printPatamter() default true;

	/**
	 * 是否打印结果参数
	 * 
	 * @return
	 */
	boolean printResult() default true;

	/**
	 * 是否打印异常参数
	 * 
	 * @return
	 */
	boolean printThrow() default true;

	/**
	 * 是否打印请求时长
	 * 
	 * @return
	 */
	boolean printSpendTime() default false;

	/**
	 * 默认参数值
	 * 
	 * @return
	 */
	String value() default "";

}