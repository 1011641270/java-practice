/**
 * This File is created by hztianduoduo at 2016年4月22日,any questions please have a message on me!
 */
package com.runtime;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年4月22日
 */
public class RuntimeDemo {

    // a class that extends thread that is to be called when program is exiting
    static class Message extends Thread {

    public void run() {
    System.out.println("Bye.");
    }
    }

    public static void main(String[] args) {
    try {

    // register Message as shutdown hook
    Runtime.getRuntime().addShutdownHook(new Message());

    // print the state of the program
    System.out.println("Program is starting...");

    // cause thread to sleep for 3 seconds
    System.out.println("Waiting for 3 seconds...");
    Thread.sleep(3000);

    // print that the program is closing 
    System.out.println("Program is closing...");


    } catch (Exception e) {
    e.printStackTrace();
    }
    }
 }
