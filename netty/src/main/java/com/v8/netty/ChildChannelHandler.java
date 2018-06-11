/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have a message on the http://tian-dd.top.
 */
package com.v8.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author hztianduoduo
 *
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
    
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
            System.out.println("客户端连接到服务端");
            
            //socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new StringEncoder());
            socketChannel.pipeline().addLast(new MyClientHanlder());
            
    }

}
