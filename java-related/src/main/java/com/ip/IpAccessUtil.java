/**
 * This File is created by hztianduoduo at 2016年1月5日,any questions please have a
 * message on the http://tian-dd.top.
 */
package com.ip;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * @author hztianduoduo
 */
public class IpAccessUtil {

    private static final Logger interLogger = LoggerFactory
            .getLogger(IpAccessUtil.class);

    // 时间段（单位毫秒）
    private long period = 1000000000L; // 1秒

    // 限制阈值
    private int threshHold = 2; // 13次

    // ip被禁时间（单位分钟）
    private int expire = 60 * 10; // 10小时

    // 时间记录
    private AtomicLong tick = new AtomicLong(System.currentTimeMillis());

    // 访问记录
    private Map<String, AtomicInteger> accessMap = new ConcurrentHashMap<String, AtomicInteger>();

    Cache<String, Object> bannedIps = CacheBuilder.newBuilder()
            .maximumSize(1000).expireAfterWrite(expire, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, Object>() {
                @Override
                public void onRemoval(
                        RemovalNotification<String, Object> entry) {
                    interLogger.info("bannedIps remove:" + entry.getKey());
                }
            }).build();

    private final Object useless = new Object();
    
    public boolean isAllowAccess(String ip) {

        if (bannedIps.getIfPresent(ip) != null) {
            interLogger.info("hit banned ip:" + ip);
            return false;
        }

        if ((System.currentTimeMillis() - tick.get()) > period) {
            tick.set(System.currentTimeMillis());
            System.out.println("accessMap:" + accessMap.size());
            accessMap.clear();
            System.out.println("accessMap:" + accessMap.size());
            return true;
        }

        if (checkAccessLimit(ip)) {
            interLogger.info("ip reach limit:" + ip);
            bannedIps.put(ip, useless);
            return false;
        }
        return true;
    }

    private boolean checkAccessLimit(String ip) {
        interLogger.info("add access history " + ip);
        AtomicInteger count = accessMap.get(ip);
        System.out.println("count:" + count);
        if (count == null) {
            accessMap.put(ip, new AtomicInteger(1));
            return false;
        } else {
            int curr = count.incrementAndGet();
            interLogger.info(ip + "," + curr);
            return curr > threshHold;
        }
    }


}
