/**
 * @(#)FilterChain.java, 18/6/20.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.filter;

/**
 * 多个filter处理
 * @author 田躲躲(tian_dd@yeah.net)
 */
public interface FilterChain {

    void add(FilterVerifyHandler filterVerifyHandler);
    boolean doFilter(FilterReqParam filterReqParam);

}