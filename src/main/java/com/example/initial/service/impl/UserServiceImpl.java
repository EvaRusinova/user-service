package com.example.initial.service.impl;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.entity.User;
import com.example.initial.exception.DatabaseException;
import com.example.initial.exception.InvalidCredentialsException;
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

  public List<User> saveAll(List<User> users) {
    return userRepository.saveAll(users);
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

  private boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  private boolean existsByUserName(String userName) {
    return userRepository.existsByUserName(userName);
  }

  public User registerUser(UserRegistrationDto userRegistrationDto) {
    if (userRegistrationDto.getAge() < 18) {
      throw new InvalidCredentialsException(
          "User must be 18 or older to register", "INVALID_CREDENTIALS");
    }

    if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
      throw new DatabaseException("Email already exists", "CONFLICT");
    }

    if (userRepository.existsByUserName(userRegistrationDto.getUserName())) {
      throw new DatabaseException("Username already exists", "CONFLICT");
    }

    User user = new User();
    user.setName(userRegistrationDto.getName());
    user.setAge(userRegistrationDto.getAge());
    user.setEmail(userRegistrationDto.getEmail());
    user.setUserName(userRegistrationDto.getUserName());
    user.setPassword(userRegistrationDto.getPassword());
    return userRepository.save(user);
  }
}
