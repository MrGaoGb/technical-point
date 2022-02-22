package com.technical.point.list.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: Mr.Gao
 * @date: 2022年01月10日 13:59
 * @description:
 */
public class MyJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始扫描数据库.....");
    }

    public static void main(String[] args) throws SchedulerException {
        final JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
        final JobDetail jobDetail = jobBuilder.withIdentity("job", "group").build();

        //创建触发器 每3秒执行一次
        final SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever()).build();

        //创建调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //将任务以及触发器放到调度器里
        scheduler.scheduleJob(jobDetail, simpleTrigger);

        //调度器开始调度任务
        scheduler.start();


    }

}
