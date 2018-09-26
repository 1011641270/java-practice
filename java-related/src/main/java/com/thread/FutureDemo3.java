/**
 * @(#)FutureDemo3.java, 18/9/6.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.thread;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 田躲躲(tian_dd)
 */
public class FutureDemo3 {

    static ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws Exception{
        Future<Map<String, String>> accountMapFuture = safelyAsyncMultiGetAccountMap();
        if(accountMapFuture != null){
            System.out.println(accountMapFuture.get());
        }

        Integer i = null;
        System.out.println(Lists.newArrayList(i));


    }

    private static Future<Map<String, String>> safelyAsyncMultiGetAccountMap() {
        Future<Map<String, String>> accountMapFuture = null;
        try {
            accountMapFuture = executor.submit(new Task());
        } catch (Exception ex) {
        }
        return accountMapFuture;
    }

    static class Task implements Callable<Map<String, String>>{

        @Override
        public Map<String, String> call() throws Exception {
            HashMap<String, String> map  = new HashMap<>();
            map.put("1","1");
            Thread.sleep(5000);
            return map;
        }

    }


}