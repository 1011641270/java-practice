/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.v7.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author hztianduoduo
 */
public class NettyClient {

    public static void main(String[] args) {
        try {
            new NettyClient().connect("127.0.0.1", 7397);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect(String inetHost, int inetPort) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();

            b.group(group); // group 组
            b.channel(NioSocketChannel.class); // channel 通道
            b.option(ChannelOption.TCP_NODELAY, true); // option 选项
            b.handler(new ChildChannelHandler()); // handler 处理

            // 发起异步链接
            ChannelFuture f = b.connect(inetHost, inetPort);

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }

    }

}
