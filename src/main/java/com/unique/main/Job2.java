package com.unique.main;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
 
/**
 * This class defines a quartz job.
 * @author w3spoint
 */
public class Job2 implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {	 
		System.out.println("Job2 is running.");		 
	}
}