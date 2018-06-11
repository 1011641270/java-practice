/**
 * This File is created by hztianduoduo at 2016年4月22日,any questions please have a message on me!
 */
package com.runtime;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年4月22日
 */
public class ShutdownHook implements Runnable{

   
    public ShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this));
        System.out.println(">>> shutdown hook registered");
    }

    public void run() {
        System.out.println("/n>>> About to execute: " + ShutdownHook.class.getName() + ".run() to clean up before JVM exits.");
        this.cleanUp();
        System.out.println(">>> Finished execution: " + ShutdownHook.class.getName() + ".run()");
    }

    private void cleanUp() {
        for(int i=0; i < 7; i++) {
            System.out.println(i);
        }
    }

    /**
     * there're couple of cases that JVM will exit, according to the Java api doc.
     * typically:
     * 1. method called: System.exit(int)
     * 2. ctrl-C pressed on the console.
     * 3. the last non-daemon thread exits.
     * 4. user logoff or system shutdown.
     * @param args
     */
    public static void main(String[] args) {

        new ShutdownHook();

        System.out.println(">>> Sleeping for 5 seconds, try ctrl-C now if you like.");

        try {
            Thread.sleep(5000);     // (-: give u the time to try ctrl-C
        } catch (InterruptedException ie) { 
            ie.printStackTrace(); 
        }

        System.out.println(">>> Slept for 10 seconds and the main thread exited.");
    }

}