package com.example.initial.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.initial.dto.UserRegistrationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void testRegisterUser() throws Exception {
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    userRegistrationDto.setFullName("John Doe");
    userRegistrationDto.setAge(30);
    userRegistrationDto.setEmail("john.doemn33@gmail.com");
    userRegistrationDto.setUsername("johndoe1235");
    userRegistrationDto.setPassword("password3321tc");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationDto)))
        .andExpect(status().is(HttpStatus.OK.value()));
  }
}
