package com.cong.jobdemo1.config.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cong.jobdemo1.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class DemoJob02 extends QuartzJobBean{


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("[executeInternal][我开始的执行了]");
    }
    
}
