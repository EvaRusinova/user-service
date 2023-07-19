package com.example.initial.producer;

import com.example.initial.event.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegistrationEventPublisher {

  private final AmqpTemplate amqpTemplate;

  public void publishUserRegistrationEvent(String username, String email) {
    UserRegistrationEvent event = new UserRegistrationEvent(username, email);
    amqpTemplate.convertAndSend("user-registration-exchange", "user-registration-key", event);
  }
}
