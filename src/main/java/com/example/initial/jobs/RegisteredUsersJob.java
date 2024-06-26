package com.example.initial.jobs;

import com.example.initial.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RegisteredUsersJob {

  private final UserRepository userRepository;

  @Scheduled(cron = "0 0 */10 * * *")
  public void countUsersRegisteredInThe10Hours() {
    LocalDateTime tenHours = LocalDateTime.now().minusHours(10);
    var userCount = userRepository.countByCreatedAtAfter(tenHours);
    log.info("We have {} users registered in total for the last 10 hours", userCount);
  }
}
