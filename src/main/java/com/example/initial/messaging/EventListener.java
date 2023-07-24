package com.example.initial.messaging;

import com.example.initial.entity.User;
import com.example.initial.event.UserRegistrationEvent;
import com.example.initial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventListener {

  private final UserRepository userRepository;

  @RabbitListener(queues = "email-verification-queue")
  public void onVerifiedEmail(UserRegistrationEvent event) {
    User user = userRepository.findByEmail(event.getEmail());
    user.setIsEmailVerified(true);
    userRepository.save(user);
  }
}
