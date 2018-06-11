/**
 * This File is created by hztianduoduo at 2015年12月21日,any questions please have
 * a message on the http://tian-dd.top.
 */
package com.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author hztianduoduo
 */
public class FutureDemo1 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        
        for(int i = 0 ; i<10 ;i++){
            
            Future<String> future = executor.submit(new Task(Long.valueOf(i)));
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

class Task implements Callable<String> {
    
    private long id;
    public Task(long id){
      this.id = id;
    }

    @Override
    public String call() throws Exception {
      System.out.printf("Thread#%s : in calln", this.id);
      return this.id + "";
    }
    
}
