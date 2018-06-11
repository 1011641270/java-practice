/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have a message on the http://tian-dd.top.
 */
package com.v7.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author hztianduoduo
 *
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
    
    @Override
    protected void initChannel(SocketChannel e) throws Exception {
            System.out.println("客户端连接到服务端");
    }

}
