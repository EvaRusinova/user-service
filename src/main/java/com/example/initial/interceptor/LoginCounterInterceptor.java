package com.example.initial.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCounterInterceptor implements HandlerInterceptor {
  private static int loginCount;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // Pre-processing logic
    loginCount++;
    return true;
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    // Post-processing logic
    System.out.println("Post handle");
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    // Cleanup logic
    System.out.println("After completion");
  }

  public int getLoginCount() {
    return loginCount;
  }
}
