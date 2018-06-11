/**
 * This File is created by hztianduoduo at 2015年12月31日,any questions please have a message on the http://tian-dd.top.
 */
package com.springloaded;

import java.util.concurrent.TimeUnit;

/**
 * @author hztianduoduo
 *
 */
public class LoadDemo {

  //jar : -javaagent:E:/workspace/Java/WebRoot/WEB-INF/lib/springloaded-1.2.5.RELEASE.jar -noverify
    public static void main(String[] args) throws InterruptedException {
        
        Reload reload = new Reload();
        
        while(true){
            reload.load();
            TimeUnit.SECONDS.sleep(3);
        }
        
    }
    
    /**
     * result:
     * load
     * load
     * load
     * reload
     * reload
     * reload
     * reload
     * reload
     * rereload
     * 
     */
    
}
