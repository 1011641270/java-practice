/**
 * This File is created by hztianduoduo at 2016年3月24日,any questions please have a message on me!
 */
package com.methodinvoke;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年3月24日
 */
public class TestMethodInvoke {
    
    
    public void test1(){
        System.out.println("test1");
    }
    
    public void test2(String name){
        System.out.println("test2"+name);
    }
    
    public void test2(Integer temp){
        System.out.println("test2" + String.valueOf(temp));
    }
    
    
    
    public static void test3(){
        System.out.println("test3");
    }
    
    public static  void test4(String name){
        System.out.println("test4"+name);
    }
    
    public static  void test4(Integer temp){
        System.out.println("test4" + String.valueOf(temp));
    }

}
