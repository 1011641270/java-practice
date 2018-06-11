package com.threadexception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 定义异常线程内容
 */
class MyExceptionThread implements UncaughtExceptionHandler {
	
	@Override
    public void uncaughtException(Thread t, Throwable e) {
        // 捕捉异常后的业务处理放在这里
        System.out.println(e);
    }
}

/**
 * 定义异常线程工厂
 */
class ExceptionThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler((UncaughtExceptionHandler) new ExceptionThread2());
        return thread;
    }
}

/**
 * 运行线程
 */
	public class ExceptionThread2 implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 抛出异常
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        // 通过我们自己写的ExceptionThreadFactory线程工厂，构造线程池
        ExecutorService executorService = Executors.newCachedThreadPool(new ExceptionThreadFactory());
        try {
            System.out.println("执行线程");
            // 启动三个线程
            executorService.execute(new ExceptionThread2());
            executorService.execute(new ExceptionThread2());
            executorService.execute(new ExceptionThread2());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("捕捉异常");
        }
    }
}