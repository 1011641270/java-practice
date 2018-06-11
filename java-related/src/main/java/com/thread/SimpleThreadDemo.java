package com.thread;

/**
 * @author hztianduoduo
 */
public class SimpleThreadDemo extends Thread {
    public void run() {
        for (int i = 0; i <= 5; i++) {
            try {
                System.out.println(currentThread().getName() + "---" + i);
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadDemo t = new SimpleThreadDemo();
        t.start(); //Java启动线程 Thread
        t.run(); //  Java线程
    }
}
