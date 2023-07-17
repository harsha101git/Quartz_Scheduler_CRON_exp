package com.example.Scheduler;

import java.text.ParseException;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class SchedulerCron {
	
	
	public void fileReadScheduler() throws ParseException, SchedulerException {

		try {

			JobDetail job = JobBuilder.newJob(SchedulerJob.class).withIdentity("FileReadJob").build();
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("FileReadtrigger", "FileReadtrigger")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 57 18 ? * * *")).build();

			/** CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("FileReadtrigger", "FileReadtrigger")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 4,8,12,18 ? * * *")
						.inTimeZone(TimeZone.getDefault())).build();

			0 57 18 ? * * *
			0 0 4,8,12,18 ? * * *
			UTC	0 34 22,2,6,12 ? * * *
      		Test  0 31 9,2,6,12 ? * * * */
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();
			sch.start();
			sch.scheduleJob(job, cronTrigger);

		} catch (SchedulerException e) {
			System.err.println(e.getMessage());
		}

	}

	

}
