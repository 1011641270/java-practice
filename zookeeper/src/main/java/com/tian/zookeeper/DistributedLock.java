/**
 * @(#)DistributedLock.java, 18/6/12.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.tian.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DistributedLock {

    /**
     * zookeeper节点的默认分隔符
     */
    private final static String SEPARATOR = "/";
    /**
     * ZK根节点
     */
    private final static String ROOT_PANDALOCK_NODE = SEPARATOR + "parndaLock";// 熊猫锁的zk根节点
    /**
     * 默认客户端连接超时时间
     */
    private static final int DEFAULT_SESSION_TIMEOUT = 5000;
    /**
     * 竞争者节点，每个想要尝试获得锁的节点都会获得一个竞争者节点
     */
    private static final String COMPETITOR_NODE = "competitorNode";
    /**
     * 统一的zooKeeper连接，在Init的时候初始化
     */
    private static ZooKeeper pandaZk = null;
    /**
     * 与zk连接成功后消除围栏
     */
    private CountDownLatch latch = new CountDownLatch(1);
    private CountDownLatch getTheLocklatch = new CountDownLatch(1);

    private String rootPath = null;

    private String lockPath = null;

    private String competitorPath = null;

    private String thisCompetitorPath = null;

    private String waitCompetitorPath = null;

    public void releaseLock()  {
        try {
            pandaZk.delete(thisCompetitorPath, -1);
        }catch (KeeperException keep){
        }catch (InterruptedException in){
        }

    }

    public boolean tryLock(){

        List<String> allCompetitorList = null;
        try {
            createComPrtitorNode();
            allCompetitorList = pandaZk.getChildren(lockPath, false);
        } catch (KeeperException e) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(allCompetitorList);
        int index = allCompetitorList.indexOf(thisCompetitorPath
                .substring((lockPath + SEPARATOR).length()));
        if (index == -1) {
            throw new RuntimeException("competitorPath not exit after create");
        } else if (index == 0) {// 如果发现自己就是最小节点,那么说明本人获得了锁
            return true;
        } else {// 说明自己不是最小节点
            return false;
        }
    }

    public void lock(){
        List<String> allCompetitorList = null;
        try {
            createComPrtitorNode();
            allCompetitorList = pandaZk.getChildren(lockPath, false);
        } catch (KeeperException e) {
        } catch (InterruptedException e) {
        }
        Collections.sort(allCompetitorList);
        int index = allCompetitorList.indexOf(thisCompetitorPath
                .substring((lockPath + SEPARATOR).length()));
        if (index == -1) {
        } else if (index == 0) {//如果发现自己就是最小节点,那么说明本人获得了锁
            return;
        } else {// 说明自己不是最小节点
            waitCompetitorPath = lockPath+SEPARATOR + allCompetitorList.get(index - 1);
            // 在waitPath上注册监听器, 当waitPath被删除时, zookeeper会回调监听器的process方法
            Stat waitNodeStat;
            try {
                waitNodeStat = pandaZk.exists(waitCompetitorPath, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        if (event.getType().equals(EventType.NodeDeleted)&&event.getPath().equals(waitCompetitorPath)) {
                            getTheLocklatch.countDown();
                        }
                    }
                });
                if (waitNodeStat==null) {//如果运行到此处发现前面一个节点已经不存在了。说明前面的进程已经释放了锁
                    return;
                }else {
                    getTheLocklatch.await();
                    return;
                }
            } catch (KeeperException e) {
            } catch (InterruptedException e) {
            }

        }

    }

    /**
     * 创建竞争者节点
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void createComPrtitorNode() throws KeeperException,
            InterruptedException {
        competitorPath = lockPath + SEPARATOR + COMPETITOR_NODE;
        thisCompetitorPath = pandaZk.create(competitorPath, null,
                Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public void connectZooKeeper(String zkhosts, String lockName)
            throws KeeperException, InterruptedException,
            IOException {

        Stat rootStat; Stat lockStat;

        if (pandaZk == null) {
            pandaZk = new ZooKeeper(zkhosts, DEFAULT_SESSION_TIMEOUT,
                    new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if (event.getState().equals(
                                    KeeperState.SyncConnected)) {
                                latch.countDown();
                            }

                        }
                    });
        }

        latch.await(); //一直等待直到线程运行完成，达到顺序执行的目的。
        rootStat = pandaZk.exists(ROOT_PANDALOCK_NODE, false);

        if (rootStat == null) {
            rootPath = pandaZk.create(ROOT_PANDALOCK_NODE, null,
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            rootPath = ROOT_PANDALOCK_NODE;
        }

        String lockNodePathString = ROOT_PANDALOCK_NODE + SEPARATOR + lockName;
        lockStat = pandaZk.exists(lockNodePathString, false);
        if (lockStat != null) {// 说明此锁已经存在
            lockPath = lockNodePathString;
        } else {
            // 创建相对应的锁节点
            lockPath = pandaZk.create(lockNodePathString, null,
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
           new Thread(() -> {
               try{
                   DistributedLock zkPandaLock = new DistributedLock();
                   zkPandaLock.connectZooKeeper("127.0.0.1:2181", "tian_dd");
                   zkPandaLock.lock();
                   System.out.println(Thread.currentThread().getName()+"在做事，做完就释放锁");
                   System.out.println(Thread.currentThread().getName()+"我做完事情了");
                   zkPandaLock.releaseLock();
               }catch (Exception e){}
           }).start();
        }

    }

}