package com.example.initial.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCounterInterceptor implements HandlerInterceptor {
  private static int loginCount;

  public boolean preHandle(
      @Nullable HttpServletRequest request,
      @Nullable HttpServletResponse response,
      @Nullable Object handler) {
    // Pre-processing logic
    loginCount++;
    return true;
  }

  public void postHandle(
      @Nullable HttpServletRequest request,
      @Nullable HttpServletResponse response,
      @Nullable Object handler,
      ModelAndView modelAndView) {
    // Post-processing logic
  }

  public void afterCompletion(
      @Nullable HttpServletRequest request,
      @Nullable HttpServletResponse response,
      @Nullable Object handler,
      Exception ex) {
    // Cleanup logic
  }

  public int getLoginCount() {
    return loginCount;
  }
}
