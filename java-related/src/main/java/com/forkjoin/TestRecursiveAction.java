/**
 * This File is created by hztianduoduo at 2015年12月18日,any questions please have a message on the http://tian-dd.top.
 */
package com.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author hztianduoduo
 *
 */
public class TestRecursiveAction {
    
    public final static ForkJoinPool mainPool = new ForkJoinPool();  
    public static final int  CRITICAL_VALUE = 10;
    
    public static void main(String[] args) {
        
        int n = 50;
        int[] a = new int[n];
        for(int i=0; i<n; i++) {
                a[i] = i;
        }
        IncDemo task = new IncDemo(a, 0, n);
        mainPool.invoke(task);
        for(int i=0; i<n; i++) {
                System.out.print(a[i]+" ");
        }
        
    }

}

class IncDemo extends RecursiveAction{

    private static final long serialVersionUID = 1L;
    
    private int a[];
    private int start;
    private int end;
    
    public IncDemo(int a[],int start,int end) {

        super();
        this.a = a;
        this.start = start;
        this.end = end;
        
    }
    
    @Override
    protected void compute() {

        if(end - start > TestRecursiveAction.CRITICAL_VALUE){
            
            int mid = (start + end)/2;
            IncDemo one = new IncDemo(a, start, mid);
            IncDemo two = new IncDemo(a, mid, end);
            invokeAll(one,two);
            
        }else {
            
            for(int i=start; i<end; i++) {
                a[i] = a[i] + 1;
            }

        }
    }
    
}