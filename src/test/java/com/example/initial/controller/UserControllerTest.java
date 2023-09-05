package com.example.initial.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.initial.dto.UserRegistrationDto;
import com.example.initial.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
@MockBeans({@MockBean(UserService.class), @MockBean(PasswordEncoder.class)})
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void testRegisterUser() throws Exception {
    UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
    userRegistrationDto.setFullName("John Doe");
    userRegistrationDto.setAge(30);
    userRegistrationDto.setEmail("johndoe@example.com");
    userRegistrationDto.setUsername("johndoe");
    userRegistrationDto.setPassword("password");

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegistrationDto)))
        .andExpect(status().is(HttpStatus.OK.value()));
  }
}
