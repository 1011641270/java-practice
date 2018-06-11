/**
 * This File is created by hztianduoduo at 2016年1月5日,any questions please have a message on the http://tian-dd.top.
 */
package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author hztianduoduo
 *
 */
public class FutureDemo2 {
    
    public static void main(String[] args) throws Exception{
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        
        for(int i = 0 ; i<10 ;i++){
            
            Future<String> future = executor.submit(new Callable<String>(){

                @Override
                public String call() throws Exception {
                    return new TaskDemo((long)(Math.random()+1)*100).print();
                }
                
            });
            
            System.out.println(future.get(10, TimeUnit.SECONDS)); //设置超时时间
            futureList.add(future);
            
        }
        
        executor.shutdown();
        
        System.out.println("主线程在执行任务");

        try {
            
            for(int i = 0 ; i < futureList.size(); i++){
                System.out.println(futureList.get(i).get());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
        
    }

}

class TaskDemo {
    
    private long id;
    public TaskDemo(long id){
      this.id = id;
    }

    public String print() throws Exception {
      System.out.printf("Thread#%s : in calln", this.id);
      return this.id + "";
    }
    
}
