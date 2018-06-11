package com.exception;

import java.util.Date;

class ExceptionDemo {

	Throwable th =  new Throwable();;
    public ExceptionDemo() {
        System.out.println("in constructor");
    }
    public void a() {
        c();
    }
    public void b() {
        System.out.println("in b");
        th.fillInStackTrace();
        th.printStackTrace(System.out);
        System.out.println("in b");
    }
    public void c(){
        b();
        th.fillInStackTrace();
        System.out.println("in c");
        th.printStackTrace(System.out);
        System.out.println("in c");
    }
    
    public void d(){
        
        try {
               int i = 100;
               int j = 0;
               int result = 0 ;
               result = i/j;
               System.out.println(result);
        } catch (Exception parseException) {
                throw new ParseException("解析出错");
        }
    }
    
    public static void main(String [] args) {
    	ExceptionDemo t3 = new ExceptionDemo();
        t3.a();
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date());
        
        t3.d();
    }
	
}