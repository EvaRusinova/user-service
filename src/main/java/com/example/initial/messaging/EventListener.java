package com.example.initial.messaging;

import com.example.initial.entity.User;
import com.example.initial.event.UserRegistrationEvent;
import com.example.initial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {

  private final UserRepository userRepository;

  @RabbitListener(queues = "email-verification-queue")
  public void onVerifiedEmail(UserRegistrationEvent event) {
    var email = event.getEmail();
    User user = userRepository.findByEmail(email);
    if(user == null) {
      log.error("User not found with email: {}", email);
      return;
    }
    user.setIsEmailVerified(true);
    userRepository.save(user);
    log.info("Email verified successfully for user with email: {}", email);
  }
}
