/**
 * This File is created by hztianduoduo at 2015年12月31日,any questions please have a message on the http://tian-dd.top.
 */
package com.guava.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.guava.cache.model.Person;


/**
 * @author hztianduoduo
 *
 */
public class TestGuavaCache {
    
    //@Ignore
    @Test
    public void testUserCacheLoader() throws Exception{
        
        @SuppressWarnings("serial")
        final List<Person> persons = new ArrayList<Person>(){{
            
            add(new Person(1, "test1"));
            add(new Person(2, "test2"));
            add(new Person(3, "test3"));
            
        }};
        
     // 创建cache
        LoadingCache<String, Person> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1, TimeUnit.MINUTES)// 给定时间内没有被读/写访问，则回收。
                // .expireAfterWrite(5, TimeUnit.SECONDS)//给定时间内没有写访问，则回收。
                // .expireAfterAccess(3, TimeUnit.SECONDS)// 缓存过期时间为3秒
                .maximumSize(100). // 设置缓存个数
                build(new CacheLoader<String, Person>() {
                    @Override
                    
                    /**  当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                     */
                    public Person load(String key) throws ExecutionException {
                        System.out.println(key + " load in cache");
                        return getPerson(key);
                    }

                    // 此时一般我们会进行相关处理，如到数据库去查询
                    private Person getPerson(String key) throws ExecutionException {
                        
                        System.out.println(key + " query");
                        
                        for (Person p : persons) {
                            if (String.valueOf(p.getId()).equals(key))
                                return p;
                        }
                        
                        return null;
                    }
                });
        
        cache.get("1");
        cache.get("2");
        cache.get("3");
        System.out.println("======= sencond time  ==========");
        cache.get("1");
        cache.get("2");
        cache.get("3");
        
        /**
         * result:
         * 1 load in cache
         * 1 query
         * 2 load in cache
         * 2 query
         * 3 load in cache
         * 3 query
======= sencond time  ==========
         * 
         */
        
    }
    
    @Ignore
    @Test
    public void testUserCallback() throws ExecutionException {
        
        @SuppressWarnings("serial")
        final List<Person> persons = new ArrayList<Person>(){{
            
            add(new Person(1, "test1"));
            add(new Person(2, "test2"));
            add(new Person(3, "test3"));
            
        }};

        final String key = "1";
        Cache<String, Person> cache2 = CacheBuilder.newBuilder().maximumSize(1000).build();
        /**
         * 用缓存中的get方法，当缓存命中时直接返回结果;否则，通过给定的Callable类call方法获取结果并将结果缓存。<br/>
         * 可以用一个cache对象缓存多种不同的数据，只需创建不同的Callable对象即可。
         */
        
        @SuppressWarnings("unused")
        Person person = cache2.get(key, new Callable<Person>() {
            public Person call() throws ExecutionException {
                System.out.println(key + " load in cache");
                return getPerson(key);
            }

            // 此时一般我们会进行相关处理，如到数据库去查询
            private Person getPerson(String key) throws ExecutionException {
                System.out.println(key + " query");
                for (Person p : persons) {
                    if (String.valueOf(p.getId()).equals(key))
                        return p;
                }
                return null;
            }
        });
        
        System.out.println("======= sencond time  ==========");
        person = cache2.getIfPresent(key);
        person = cache2.getIfPresent(key);
    }
    
    
    @Test
    public void testListener() throws ExecutionException {
        
        CacheLoader<String, Person> loader = new CacheLoader<String, Person>() {
            @Override
            // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
            public Person load(String key) throws ExecutionException {
                System.out.println(key + " load in cache");
                return getPerson(key);
            }
            // 此时一般我们会进行相关处理，如到数据库去查询
            private Person getPerson(String key) throws ExecutionException {
                System.out.println(key + " query");
                return new Person(Integer.parseInt(key), "zhang" + key);
            }
        };

        // remove listener
        RemovalListener<String, Person> removalListener = new RemovalListener<String, Person>() {
            public void onRemoval(RemovalNotification<String, Person> removal) {
                System.out.println("cause:" + removal.getCause() + " key:" + removal.getKey() + " value:"
                        + removal.getValue());
            }
        };

        LoadingCache<String, Person> cache = CacheBuilder.newBuilder()//
                .expireAfterWrite(2, TimeUnit.MINUTES).maximumSize(1024).removalListener(removalListener).build(loader);
        
        cache.get("1");// 放入缓存
        cache.get("1");// 第二次获取(此时从缓存中获取)
        cache.invalidate("1");// 移除缓存
        cache.get("1");// 重新获取
        cache.get("1");// 再次获取(此时从缓存中获取)
    }

}
