package com.example.initial.config;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@AllArgsConstructor
public class RabbitMQConfig {

  // RabbitMQ connection properties
  private static final String RABBITMQ_HOST = "localhost";
  private static final int RABBITMQ_PORT = 5672;
  private static final String RABBITMQ_USERNAME = "guest";
  private static final String RABBITMQ_PASSWORD = "guest";
  private static final String RABBITMQ_VIRTUAL_HOST = "/";
  private static final String USER_REGISTRATION_EXCHANGE = "user-registration-exchange";

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(RABBITMQ_HOST);
    connectionFactory.setPort(RABBITMQ_PORT);
    connectionFactory.setUsername(RABBITMQ_USERNAME);
    connectionFactory.setPassword(RABBITMQ_PASSWORD);
    connectionFactory.setVirtualHost(RABBITMQ_VIRTUAL_HOST);
    return connectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public Exchange userRegistrationExchange() {
    return new DirectExchange(USER_REGISTRATION_EXCHANGE);
  }
}
