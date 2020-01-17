package com.rquest.test.ftputil;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class GetWhiteListtask implements Runnable
{
    private BussinessUtil bussinessUtil;
    
    public GetWhiteListtask(BussinessUtil bussinessUtil)
    {
        super();
        this.bussinessUtil = bussinessUtil;
    }
    
    public void run()
    {
        try
        {
            String[] data = (ConfigConst.rundate).split("-");
            String rundate = null;
            if (data.length == 1)
            {
                rundate = "0 " + "*/" + data[0] + " * * * ?";
            }
            else
            {
                if (data[0].equals("*"))
                {
                    rundate = "0 " + data[2] + " " + data[1] + " * * ?";
                }
                else
                {
                    rundate = "0 " + data[2] + " " + data[1] + " " + data[0] + " * ?";
                }
            }
            Quartz quartz = new Quartz();
            SchedulerFactory sf = new StdSchedulerFactory();
            String JOB_GROUP_NAME = "group1";
            String TRIGGER_GROUP_NAME = "trigger1";
            String jobName = "11";
            Scheduler sched = sf.getScheduler();
            JobDetail jobDetail = new JobDetailImpl(jobName, JOB_GROUP_NAME, quartz.getClass());// 任务名，任务组，任务执行类
            jobDetail.getJobDataMap().put("myjob", bussinessUtil);
            // 触发器
            CronTriggerImpl trigger = new CronTriggerImpl(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
            
            trigger.setCronExpression(rundate);
            // 触发器时间设定
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown())
            {
                sched.start();
            }
            
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SchedulerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
