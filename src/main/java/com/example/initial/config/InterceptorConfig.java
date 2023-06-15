package com.example.initial.config;

import com.example.initial.interceptor.LoginCounterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Bean
  public LoginCounterInterceptor loginCounterInterceptor() {
    return new LoginCounterInterceptor();
  }

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCounterInterceptor()).addPathPatterns("/login");
  }
}
