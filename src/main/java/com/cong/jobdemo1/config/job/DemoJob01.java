package com.cong.jobdemo1.config.job;

import java.util.concurrent.TimeUnit;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cong.jobdemo1.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class DemoJob01 extends QuartzJobBean{

    @Autowired
    private DemoService demoService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("准备执行，耗时任务");
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            log.error("线程中断", e);
            Thread.currentThread().interrupt();
        }
        log.info("[executeInternal][我开始执行了, demoService 为 ({})]", demoService);
    }
    
}
