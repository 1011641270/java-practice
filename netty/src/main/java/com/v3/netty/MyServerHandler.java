/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.v3.netty;

import java.util.Date;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author hztianduoduo
 */
public class MyServerHandler extends ChannelHandlerAdapter {

    /*
     * channelAction channel 通道 action 活跃的
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().localAddress().toString() + " channelActive");

    }

    /*
     * channelInactive channel 通道 Inactive 不活跃的
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println(ctx.channel().localAddress().toString() + " channelInactive");

    }

    /*
     * channelRead channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
     * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
     */
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(new Date()+" "+msg);  //不需要手动解码
    }

    /*
     * channelReadComplete channel 通道 Read 读取 Complete 完成
     * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     */
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.flush();
    }

    /*
     * exceptionCaught exception 异常 Caught 抓住
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

}
