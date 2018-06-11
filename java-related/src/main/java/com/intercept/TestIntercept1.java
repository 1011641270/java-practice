/**
 * @Title TestIntercept.java
 * @Project Java
 * @Package com.intercept
 * @author hztianduoduo
 * @Date 2016年10月12日
 * @version V1.0
 *
 * Copyright 2016 Netease, Inc. All rights reserved.
 */
package com.intercept;

/**
 * @author hztianduoduo
 *
 */
public class TestIntercept1 implements Interceptor{

	@Override
	public void test() {
		System.out.println("test...before");
	}

}
