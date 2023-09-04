package com.example.initial.service.impl;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.entity.User;
import com.example.initial.event.UserRegistrationEvent;
import com.example.initial.messaging.EventPublisher;
import com.example.initial.repository.UserRepository;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  @Value("${rabbitmq.exchanges.user-registration-ex}")
  private String userRegistrationEx;

  @Value("${rabbitmq.routing-keys.user-registration-rk}")
  private String userRegistrationRoutingKey;

  private final UserRepository userRepository;
  private final EventPublisher eventPublisher;
  private final PasswordEncoder passwordEncoder;

  public void saveAll(List<User> users) {
    userRepository.saveAll(users);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  public User registerUser(UserRegistrationDto userRegistrationDto) {
    var user =
        User.builder()
            .fullName(userRegistrationDto.getFullName())
            .age(userRegistrationDto.getAge())
            .email(userRegistrationDto.getEmail())
            .username(userRegistrationDto.getUsername())
            .password(this.passwordEncoder.encode(userRegistrationDto.getPassword()))
            .build();

    user = userRepository.save(user);

    log.info(
        "Successful registration for username: {}, with an email: {}. You're logged in!",
        user.getUsername(),
        user.getEmail());

    UserRegistrationEvent event = new UserRegistrationEvent(user.getFullName(), user.getEmail());
    eventPublisher.publishEvent(userRegistrationEx, userRegistrationRoutingKey, event);
    return user;
  }
}
