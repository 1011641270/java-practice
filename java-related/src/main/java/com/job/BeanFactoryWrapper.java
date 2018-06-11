/**
 * This File is created by hztianduoduo at 2016年3月22日,any questions please have a message on me!
 */
package com.job;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年3月22日
 */
public class BeanFactoryWrapper {
    
    private static BeanFactory ctx = null;

    public final static BeanFactory getCtx() {
        if (ctx == null) {
            ctx = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext-Resource.xml" });
        }
        return ctx;
    }

    public final static Object getBean(String name) {
        return getCtx().getBean(name);
    }
    

}
