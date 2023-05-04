package com.unique.main;

import java.util.Date;
import java.util.List;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
 

public class CronTriggerTest {
	public static void main(String args[]){
    	try{
    		//Set job details.
    		JobDetail job1 = JobBuilder.newJob(Job1.class)
    			.withIdentity("job1", "group1").build();
 
    		JobDetail job2 = JobBuilder.newJob(Job2.class)
    			.withIdentity("job2", "group1").build();
 
    		JobDetail job3 = JobBuilder.newJob(Job3.class)
    			.withIdentity("job3", "group2").build();
 
        	//Set the scheduler timings.
    		Trigger trigger1 = TriggerBuilder.newTrigger()
    			.withIdentity("cronTrigger1", "group1")
    			.withSchedule(CronScheduleBuilder
    			  .cronSchedule("0/10 * * * * ?")).build();
 
    		Trigger trigger2 = TriggerBuilder.newTrigger()
    			.withIdentity("cronTrigger2", "group1")
    			.withSchedule(CronScheduleBuilder
    			  .cronSchedule("0/10 * * * * ?")).build();
 
    		Trigger trigger3 = TriggerBuilder.newTrigger()
    			.withIdentity("cronTrigger3", "group1")
    			.withSchedule(CronScheduleBuilder
    			  .cronSchedule("0/10 * * * * ?")).build();
 
        	//Execute the job.
    		Scheduler scheduler = 
    			new StdSchedulerFactory().getScheduler();
        	scheduler.start();
        	scheduler.scheduleJob(job1, trigger1);
        	scheduler.scheduleJob(job2, trigger2);
        	scheduler.scheduleJob(job3, trigger3);
 
        	//List all quartz jobs
        	for (String groupName : scheduler.getJobGroupNames()) {
       	         for (JobKey jobKey : scheduler.getJobKeys(
    	    		 GroupMatcher.jobGroupEquals(groupName))) {    	 
    		  String jobName = jobKey.getName();
    		  String jobGroup = jobKey.getGroup();
 
    		  List triggers = (List) 
    				  scheduler.getTriggersOfJob(jobKey);
    		  Date nextFireTime = ((Trigger) triggers.get(0)).getNextFireTime();
    		  System.out.println("Job : " + jobName + ", Group : "
    				+ jobGroup + ", Next execution time : "
    					+ nextFireTime); 
   	      }    	 
    	   }
    	}catch(Exception e){
    		e.printStackTrace();
    	}    	
    }
}
