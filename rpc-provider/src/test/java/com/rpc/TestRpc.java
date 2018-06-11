/**
 * @(#)TestRpc.java, 18/6/11.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rpc;

import com.rpc.client.RpcProxy;
import com.rpc.inter.RpcServiceDemo;
import com.rpc.server.RpcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 田躲躲(hztianduoduo@corp.netease.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rpc-context.xml"})
public class TestRpc {

    @Autowired
    private RpcProxy rpcProxy;

    @Autowired
    private RpcServiceDemo rpcServiceDemo;

    @Test
    public void testRpc(){

       RpcServiceDemo rpcProxyService = rpcProxy.create(RpcService.class);
       rpcProxyService.sayRpcService("tian_dd");
       rpcServiceDemo.sayRpcService("tian_dd");

    }

}