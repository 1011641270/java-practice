/**
 * @(#)RpcServiceImpl.java, 18/6/11.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rpc.impl;

import com.rpc.inter.RpcServiceDemo;
import com.rpc.server.RpcService;

/**
 * @author 田躲躲(hztianduoduo@corp.netease.com)
 */
@RpcService(RpcServiceDemo.class)
public class RpcServiceDemoImpl implements RpcServiceDemo{

    @Override
    public void sayRpcService(String name) {
        System.out.println(name);
    }
}