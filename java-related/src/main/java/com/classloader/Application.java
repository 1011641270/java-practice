package com.classloader;

/**
 * @author hztianduoduo
 *
 */
public class Application {

        public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
            
            
            /**Result : Hello from static block **/
            new Test();
            
            Class test1 = Class.forName("com.classloader.Test");
            
            Class<?> clazz1 = Thread.currentThread().getContextClassLoader()
                    .loadClass("com.classloader.Test");
            clazz1.newInstance();
            
            /**Result : Hello from static block  Demo**/
            
            new Test.Demo();
            
            Class test2 = Class.forName("com.classloader.Test$Demo");
            
            Class<?> clazz2 = Thread.currentThread().getContextClassLoader()
                    .loadClass("com.classloader.Test$Demo");
            clazz2.newInstance();
            
        }
    
}
