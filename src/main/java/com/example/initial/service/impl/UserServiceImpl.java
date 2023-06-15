package com.example.initial.service.impl;

import com.example.initial.entity.User;
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

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public List<User> saveAll(List<User> users) {
    return userRepository.saveAll(users);
  }
}
