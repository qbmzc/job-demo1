package com.cong.jobdemo1;

import org.junit.jupiter.api.Test;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cong.jobdemo1.config.job.DemoJob01;
import com.cong.jobdemo1.config.job.DemoJob02;

@SpringBootTest
class JobDemo1ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
    private Scheduler scheduler;

    @Test
    public void addDemoJob01Config() throws SchedulerException {
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(DemoJob01.class)
                .withIdentity("demoJob01") // 名字为 demoJob01
                .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                .build();
        // 创建 Trigger
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5) // 频率。
                .repeatForever(); // 次数。
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail) // 对应 Job 为 demoJob01
                .withIdentity("demoJob01Trigger") // 名字为 demoJob01Trigger
                .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                .build();
        // 添加调度任务
        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.scheduleJob(jobDetail, Sets.newSet(trigger), true);
    }

    @Test
    public void addDemoJob02Config() throws SchedulerException {
        // 创建 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(DemoJob02.class)
                .withIdentity("demoJob02") // 名字为 demoJob02
                .storeDurably() // 没有 Trigger 关联的时候任务是否被保留。因为创建 JobDetail 时，还没 Trigger 指向它，所以需要设置为 true ，表示保留。
                .build();
        // 创建 Trigger
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail) // 对应 Job 为 demoJob01
                .withIdentity("demoJob02Trigger") // 名字为 demoJob01Trigger
                .withSchedule(scheduleBuilder) // 对应 Schedule 为 scheduleBuilder
                .build();
        // 添加调度任务
        scheduler.scheduleJob(jobDetail, trigger);
//        scheduler.scheduleJob(jobDetail, Sets.newSet(trigger), true);
    }

}
