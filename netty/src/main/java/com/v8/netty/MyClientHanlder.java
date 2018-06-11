/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.v8.netty;

/**
 * @author hztianduoduo
 */
import java.util.Date;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MyClientHanlder extends ChannelHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端与服务端通道-开启：" + ctx.channel().localAddress()
                + "channelActive");

    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("客户端与服务端通道-关闭：" + ctx.channel().localAddress()
                + "channelInactive");

    }
    
    
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {

        // 注意此处已经不需要手工解码了
        System.out.println(ctx.channel().id() + "" + new Date() + " " + msg);
        String str = (String) msg + "\r\n";

        // 发送给服务端
        ctx.writeAndFlush(str);
    }

    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("异常退出:" + cause.getMessage());
    }

}
