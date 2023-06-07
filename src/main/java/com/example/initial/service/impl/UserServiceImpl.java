package com.example.initial.service.impl;

import com.example.initial.entity.User;
import com.example.initial.repository.UserRepository;
import com.example.initial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

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
    userRepository.deleteById(id);
  }
}
