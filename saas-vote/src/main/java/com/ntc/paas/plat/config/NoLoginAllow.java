package com.ntc.paas.plat.config;

import java.lang.annotation.*;

/**
 * 区分公共资源和普通资源的 添加了这个注解就表示是公共的资源
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Documented
public @interface NoLoginAllow {

	boolean required() default true;
}
