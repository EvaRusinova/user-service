package com.example.initial.service.impl;

import com.example.initial.dto.UserLoginDto;
import com.example.initial.entity.User;
import com.example.initial.repository.UserRepository;
import com.example.initial.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserLoginDto userLoginDto;

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public User registerUser(User user) {
    return userRepository.save(user);
  }

  public List<User> saveAll(List<User> users) {
    return userRepository.saveAll(users);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public void deleteById(Long id) {
    log.warn("Harmful action warning for username: {}", userLoginDto.getUserName());
    userRepository.deleteById(id);
  }
}
