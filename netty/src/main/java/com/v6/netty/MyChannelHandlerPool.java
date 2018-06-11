/**
 * This File is created by hztianduoduo at 2015年12月22日,any questions please have a message on the http://tian-dd.top.
 */
package com.v6.netty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author hztianduoduo
 *
 */
public class MyChannelHandlerPool {
    
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
