/**
 * @Title Before.java
 * @Project Java
 * @Package com.annotation
 * @author hztianduoduo
 * @Date 2016年10月12日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */
package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.intercept.Interceptor;


@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Before {
	Class<? extends Interceptor>[] value();
}


