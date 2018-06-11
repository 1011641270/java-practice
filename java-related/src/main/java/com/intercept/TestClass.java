/**
 * @Title TestClass.java
 * @Project Java
 * @Package com.intercept
 * @author hztianduoduo
 * @Date 2016年10月12日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */
package com.intercept;

import java.lang.reflect.Method;
import java.util.Map;

import com.annotation.After;
import com.annotation.Before;

/**
 * @author hztianduoduo
 *
 */

@Before(TestIntercept1.class)
@After(TestIntercept2.class)

public class TestClass {

	public void testInter(){
		System.out.println("test inter....");
	}
	
	public static void main(String[] args) throws Exception {
		
		Class clazz = TestClass.class;
		Object object = clazz.newInstance();
		
		Before before = (Before) clazz.getAnnotation(Before.class);
		After after = (After) clazz.getAnnotation(After.class);
		
		handlerBeforeIntercepter(before);
		handlerAfterIntercepter(after);
		
		if(!isIntersMapNull(InterCeptorMapping.getBeforeIntersMap())){
			invokeHandler(InterCeptorMapping.getBeforeIntersMap());
		}
		
		Method method = clazz.getMethod("testInter", null);
		method.invoke(object, null);

		if(!isIntersMapNull(InterCeptorMapping.getAfterIntersMap())){
			invokeHandler(InterCeptorMapping.getAfterIntersMap());
		}
		
	}
	
	private static void handlerBeforeIntercepter(Before before) throws InstantiationException, IllegalAccessException{
		
		Class<? extends Interceptor>[] interceptorClasses = before.value();
		
		for (Class<? extends Interceptor> class1 : interceptorClasses) {
			InterCeptorMapping.addBeforeInter(class1, class1.newInstance());
		}
		
	}
	
	private static void handlerAfterIntercepter(After after) throws Exception{
		
		Class<? extends Interceptor>[] interceptorClasses = after.value();
		
		for (Class<? extends Interceptor> class1 : interceptorClasses) {
			InterCeptorMapping.addAfterInter(class1, class1.newInstance());
		}
		
	}
	
	private static void invokeHandler(Map<Class<? extends Interceptor>, Interceptor> intersMap) throws Exception{
		
		for (Interceptor interceptor: intersMap.values()) {
			interceptor.getClass().newInstance().test();
		}
		
	}
	
	private static boolean isIntersMapNull(Map<Class<? extends Interceptor>, Interceptor> intersMap){
		return intersMap.size()==0 || intersMap == null;
	}
	
}
