package com.example.initial.jobs;

import com.example.initial.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationCountJob implements Job {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationCountJob.class);

  private UserService userService;

  public void execute(JobExecutionContext context) throws JobExecutionException {
    long userCount = userService.countUsersRegisteredInTheLastSixMinutes();
    try {
      Thread.sleep(20000); // Sleep for 20 seconds
    } catch (InterruptedException e) {
      throw new JobExecutionException(e);
    }
    LOGGER.info("For the last 6 minutes {} users registered", userCount);
  }
}
