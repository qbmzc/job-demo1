package com.cong.jobdemo1.controller;

import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cong.jobdemo1.config.job.DemoJob03;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private Scheduler scheduler;

    @SneakyThrows
    @GetMapping("add")
    public void getMethodName() {
        log.info("添加❤️的任务");
        JobDetail jobDetail = JobBuilder.newJob(DemoJob03.class)
                .withIdentity(UUID.randomUUID().toString())
                .storeDurably()
                .build();

        SimpleScheduleBuilder repeatForever = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity(UUID.randomUUID().toString())
                .withSchedule(repeatForever)
                .build();
        /*
         * 如果想要覆盖数据库中的 Quartz 定时任务的配置，
         * 可以调用 Scheduler#scheduleJob(JobDetail jobDetail, Set<? extends Trigger>
         * triggersForJob, boolean replace) 方法，
         * 传入 replace = true 进行覆盖配置。
         */
        scheduler.scheduleJob(jobDetail, simpleTrigger);

        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

}
