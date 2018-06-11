/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have a message on the http://tian-dd.top.
 */
package com.v2.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author hztianduoduo
 *
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

    
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
    
        System.out.println("客户端IP" + socketChannel.localAddress().getHostName());
        System.out.println("客户端端口" + socketChannel.localAddress().getPort());
        
        socketChannel.pipeline().addLast(new MyServerHandler());
        
    }

}
