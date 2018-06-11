/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.v9.netty.service;

/**
 * @author hztianduoduo
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.v9.netty.handler.NettyServer;

@Service
public class NettyExecutorService {

    public NettyExecutorService() {

        System.err.println("---------- Spring自动加载         ---------- ");

        /*
         * 说明 如果此处不用线程池，直接用server.run()启动【直接调用方法而已】
         * 那么你会看到tomcat启动过程中，在启动了Netty后就会一直等待连接
         */
        NettyServer server = new NettyServer();
        // 线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 启动线程池
        es.execute(server);

    }

}
