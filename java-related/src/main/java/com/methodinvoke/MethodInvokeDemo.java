/**
 * This File is created by hztianduoduo at 2016年3月24日,any questions please have a message on me!
 */
package com.methodinvoke;

import java.lang.reflect.Method;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年3月24日
 */
public class MethodInvokeDemo {
    
    public static void main(String[] args) throws Exception{
        
        invokeMethod(TestMethodInvoke.class.newInstance(), "test1", new Object[]{});
        invokeMethod(TestMethodInvoke.class.newInstance(),"test2",new Object[]{new String("2")});
        invokeMethod(TestMethodInvoke.class.newInstance(),"test2",new Object[]{new Integer(2)});
        invokeMethod(TestMethodInvoke.class.newInstance(),"test3",new Object[]{});
        invokeMethod(TestMethodInvoke.class.newInstance(),"test4",new Object[]{new String("4")});
        invokeMethod(TestMethodInvoke.class.newInstance(),"test4",new Object[]{new Integer(2)});
        
        
    }

    
    public static Object invokeMethod(Object owner, String methodName,
            Object[] args) throws Exception {

        Class ownerClass = owner.getClass();

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(owner, args);
    }

    public Object invokeStaticMethod(String className, String methodName,
            Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);

        Class[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Method method = ownerClass.getMethod(methodName, argsClass);

        return method.invoke(null, args);
    }

}
