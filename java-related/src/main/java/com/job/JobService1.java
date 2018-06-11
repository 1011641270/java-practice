/**
 * This File is created by hztianduoduo at 2016年3月22日,any questions please have a message on me!
 */
package com.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * @author hztiandd@tian-dd.top
 * 
 * 2016年3月22日
 */
@Service("jobService1")
public class JobService1 implements Job{
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        System.out.println("execute job1");
        
    }

}
