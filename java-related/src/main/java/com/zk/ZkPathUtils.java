/**
 * @(#)ZkPathUtils.java, 2018/12/28.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

/**
 * @author 田躲躲(tian_dd@yeah.et)
 */
public class ZkPathUtils implements Watcher{

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{

        String zkUrl = "XXXXX";
        zooKeeper = new ZooKeeper(zkUrl, 5000, new ZkPathUtils());
        List<String> childrenList = zooKeeper.getChildren("/kl-tomcat", true);
        System.out.println("同步getChildren获得数据结果：" + childrenList);

    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if(Event.EventType.None == event.getType() && null == event.getPath()){
            } else if (event.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    System.out.println("重新获得Children，并注册监听：" + zooKeeper.getChildren(event.getPath(),true));
                } catch (Exception e) {
                }
            }
        }
    }
}