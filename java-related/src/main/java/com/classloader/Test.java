/**
 * This File is created by hztianduoduo at 2016年1月7日,any questions please have a
 * message on the http://tian-dd.top.
 */
package com.classloader;

/**
 * @author hztianduoduo
 */
public class Test{

    static {
        System.out.print("Hello from static block   ");
    }
    
    public static class Demo{
        
        static{
            System.out.println("Demo");
        }
    }
}

