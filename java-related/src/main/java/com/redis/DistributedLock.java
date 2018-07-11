/**
 * @(#)DistributedLock.java, 18/7/11.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 田躲躲(tian_dd@yeah.net)
 */
public class DistributedLock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    private static void validParam(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {
        if (null == jedisPool) {
            throw new IllegalArgumentException("jedisPool obj is null");
        }

        if (null == lockKey || "".equals(lockKey)) {
            throw new IllegalArgumentException("lock key  is blank");
        }

        if (null == requestId || "".equals(requestId)) {
            throw new IllegalArgumentException("requestId is blank");
        }

        if (expireTime < 0) {
            throw new IllegalArgumentException("expireTime is not allowed less zero");
        }
    }

    /**
     *
     * @param jedisPool
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public static boolean tryLock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {

        validParam(jedisPool, lockKey, requestId, expireTime);

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            //nxxx为模式，这里我们设置为NX，意思是说如果key不存在则插入该key对应的value并返回OK，否者什么都不做返回null；
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return false;
    }

    /**
     *
     * @param jedisPool
     * @param lockKey
     * @param requestId
     * @param expireTime
     */
    public static void lock(JedisPool jedisPool, String lockKey, String requestId, int expireTime) {

        validParam(jedisPool, lockKey, requestId, expireTime);

        while (true) {
            if (tryLock(jedisPool, lockKey, requestId, expireTime)) {
                return;
            }
        }
    }

    /**
     *
     * @param jedisPool
     * @param lockKey
     * @param requestId
     * @return
     */
    public static boolean unLock(JedisPool jedisPool, String lockKey, String requestId) {

        validParam(jedisPool, lockKey, requestId, 1);

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            Object result = jedis.eval(script, Collections.singletonList(lockKey),
                    Collections.singletonList(requestId));

            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        return false;

    }

}