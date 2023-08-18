package com.example.initial.service;

import com.example.initial.entity.User;
import com.example.initial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceSecurity implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User detailsUser = userRepository.findByUsername(username);
    if (detailsUser == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    System.out.println("Fetched User: " + detailsUser);
    System.out.println("Fetched Username: " + detailsUser.getUsername());
    return org.springframework.security.core.userdetails.User.withUsername(
            detailsUser.getUsername())
        .password(detailsUser.getPassword())
        .roles(detailsUser.getRoles().name())
        .build();
  }
}
