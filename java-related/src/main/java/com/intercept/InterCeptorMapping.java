/**
 * @Title InterCeptorMapping.java
 * @Project Java
 * @Package com.intercept
 * @author hztianduoduo
 * @Date 2016年10月12日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */
package com.intercept;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hztianduoduo
 *
 */
public class InterCeptorMapping {

	private static Map<Class<? extends Interceptor>, Interceptor> beforeIntersMap = new HashMap<Class<? extends Interceptor>, Interceptor>();
	private static Map<Class<? extends Interceptor>, Interceptor> afterIntersMap = new HashMap<Class<? extends Interceptor>, Interceptor>();
	
	public static  void addBeforeInter(Class<? extends Interceptor> interceptorClass, Interceptor interceptor){
		beforeIntersMap.put(interceptorClass, interceptor);
	}
	
	public static Map<Class<? extends Interceptor>, Interceptor> getBeforeIntersMap() {
		return beforeIntersMap;
	}
	
	public static  void addAfterInter(Class<? extends Interceptor> interceptorClass, Interceptor interceptor){
		afterIntersMap.put(interceptorClass, interceptor);
	}
	
	public static Map<Class<? extends Interceptor>, Interceptor> getAfterIntersMap() {
		return afterIntersMap;
	}
	
}
