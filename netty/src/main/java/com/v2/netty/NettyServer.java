/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.v2.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author hztianduoduo
 */

public class NettyServer {
    public static void main(String[] args) {

        try {
            System.out.println("服务端开启等待客户端链接");
            new NettyServer().bing(7397);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void bing(int port) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.childHandler(new ChildChannelHandler());

            // 绑定端口
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
