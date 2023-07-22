package com.example.initial.config;

import com.example.initial.jobs.UserRegistrationCountJob;
import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
@AllArgsConstructor
public class QuartzConfig {

  private ApplicationContext applicationContext;

  @Bean
  public JobDetailFactoryBean jobDetail() {
    JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
    jobDetailFactory.setJobClass(UserRegistrationCountJob.class);
    jobDetailFactory.setDescription("Invoke User Registration Job service...");
    jobDetailFactory.setDurability(true);
    return jobDetailFactory;
  }

  @Bean
  public CronTriggerFactoryBean userCountTrigger() {
    CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
    trigger.setJobDetail(jobDetail().getObject());
    trigger.setCronExpression("0 0/5 * * * ?"); // executed every 5 minutes
    trigger.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
    return trigger;
  }

  @Bean
  public SchedulerFactoryBean scheduler(
      Trigger trigger, JobDetail job, SpringBeanJobFactory springBeanJobFactory) {
    SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
    schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
    schedulerFactory.setJobFactory(springBeanJobFactory);
    schedulerFactory.setJobDetails(job);
    schedulerFactory.setTriggers(trigger);
    return schedulerFactory;
  }

  @Bean
  public SpringBeanJobFactory springBeanJobFactory() {
    AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
    jobFactory.setApplicationContext(applicationContext);
    return jobFactory;
  }
}
