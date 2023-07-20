package com.example.initial.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventPublisher {

  private final AmqpTemplate amqpTemplate;

  public void publishEvent(String exchange, String routingKey, Object event) {
    amqpTemplate.convertAndSend(exchange, routingKey, event);
  }
}
