package com.example.initial.service.impl;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.entity.User;
import com.example.initial.event.UserRegistrationEvent;
import com.example.initial.messaging.EventPublisher;
import com.example.initial.repository.UserRepository;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EventPublisher eventPublisher;

  public void saveAll(List<User> users) {
    userRepository.saveAll(users);
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User registerUser(UserRegistrationDto userRegistrationDto) {
    var user =
        User.builder()
            .fullName(userRegistrationDto.getFullName())
            .age(userRegistrationDto.getAge())
            .email(userRegistrationDto.getEmail())
            .userName(userRegistrationDto.getUserName())
            .password(userRegistrationDto.getPassword())
            .build();
    log.info(
        "Successful registration for username: {}, with an email: {}. You're logged in!",
        user.getUserName(),
        user.getEmail());

    user = userRepository.save(user);

    UserRegistrationEvent event = new UserRegistrationEvent(user.getFullName(), user.getEmail());
    eventPublisher.publishEvent("user-registration-exchange", "user-registration-key", event);
    return user;
  }
}
