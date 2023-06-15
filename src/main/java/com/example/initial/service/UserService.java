package com.example.initial.service;

import com.example.initial.entity.User;
import java.util.List;

public interface UserService {

  List<User> saveAll(List<User> users);

  User findByUserName(String userName);

  User findById(Long userId);
}
