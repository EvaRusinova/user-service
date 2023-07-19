package com.example.initial.service.impl;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.entity.User;
import com.example.initial.producer.UserRegistrationEventPublisher;
import com.example.initial.repository.UserRepository;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserRegistrationEventPublisher eventPublisher;

  public void saveAll(List<User> users) {
    userRepository.saveAll(users);
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public User findById(Long userId) {
    return userRepository.findById(userId).orElseThrow();
  }

  private User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  private Boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  private Boolean existsByUserName(String userName) {
    return userRepository.existsByUserName(userName);
  }

  public User registerUser(UserRegistrationDto userRegistrationDto) {
    User user =
        User.builder()
            .name(userRegistrationDto.getName())
            .age(userRegistrationDto.getAge())
            .email(userRegistrationDto.getEmail())
            .userName(userRegistrationDto.getUserName())
            .password(userRegistrationDto.getPassword())
            .build();
    log.info(
        "Successful registration for username: {}, with an email: {}. You're logged in!",
        userRegistrationDto.getUserName(),
        userRegistrationDto.getEmail());

    eventPublisher.publishUserRegistrationEvent(user.getUserName(), user.getEmail());

    return userRepository.save(user);
  }
}
