package com.job;

import org.springframework.stereotype.Component;

/**
 * @author hztianduoduo
 */
@Component
public class CrondJob {

    //@Scheduled(cron = "*/5 * * * * ?")
    public void crondTest() {
        System.out.println("crond is doing....");
    }

}
