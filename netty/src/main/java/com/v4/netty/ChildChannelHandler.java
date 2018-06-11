/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have a message on the http://tian-dd.top.
 */
package com.v4.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author hztianduoduo
 *
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

    
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
    
        System.out.println("客户端IP" + socketChannel.localAddress().getHostName());
        System.out.println("客户端端口" + socketChannel.localAddress().getPort());
        
     // 解码器
        // 基于换行符号
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        
        // 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
//      socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, false, Delimiters.lineDelimiter()));
        
        // 基于最大长度
//      socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        
        // 解码转Sring
        //socketChannel.pipeline().addLast(new StringDecoder());
        
        // 在管道中添加我们自己的接收数据实现方法
        socketChannel.pipeline().addLast(new MyServerHandler());
        
    }

}
