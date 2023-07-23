package com.example.initial.service;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.entity.User;
import java.util.List;

public interface UserService {

  void saveAll(List<User> users);

  User findByUserName(String userName);

  User findById(Long userId);

  User registerUser(UserRegistrationDto userRegistrationDto);
}
