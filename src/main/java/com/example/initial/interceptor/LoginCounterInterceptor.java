package com.example.initial.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCounterInterceptor implements HandlerInterceptor {
  private int loginCount;

  public boolean preHandle(HttpServletRequest request, HttpServletRequest response, Object handler)
      throws Exception {
    // Increment login count
    loginCount++;
    return true; // Continue processing
  }

  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {}

  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {}

  public int getLoginCount() {
    return loginCount;
  }
}
