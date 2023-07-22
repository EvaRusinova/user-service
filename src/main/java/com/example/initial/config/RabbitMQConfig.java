package com.example.initial.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMQConfig {

  @Value("${rabbitmq.host}")
  private String rabbitmqHost;

  @Value("${rabbitmq.port}")
  private int rabbitmqPort;

  @Value("${rabbitmq.username}")
  private String rabbitmqUsername;

  @Value("${rabbitmq.password}")
  private String rabbitmqPassword;

  @Value("${rabbitmq.virtualHost}")
  private String rabbitmqVirtualHost;

  private static final String USER_REGISTRATION_EXCHANGE = "user-registration-exchange";

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmqHost);
    connectionFactory.setPort(rabbitmqPort);
    connectionFactory.setUsername(rabbitmqUsername);
    connectionFactory.setPassword(rabbitmqPassword);
    connectionFactory.setVirtualHost(rabbitmqVirtualHost);
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
