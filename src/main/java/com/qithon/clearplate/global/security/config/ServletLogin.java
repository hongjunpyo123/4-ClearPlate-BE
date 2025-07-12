package com.qithon.clearplate.global.security.config;

import com.qithon.clearplate.global.security.jwt.JwtTokenProvider;
import jakarta.persistence.Column;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServletLogin {

  private final JwtTokenProvider jwtTokenProvider;


  public Long extractUserIdFromRefreshToken(HttpServletRequest request) {
    String refreshToken = extractRefreshTokenFromCookies(request);
    if (refreshToken == null) {
      throw new RuntimeException("Refresh token이 없습니다.");
    }
    return jwtTokenProvider.getUserIdFromToken(refreshToken);
  }

  private String extractRefreshTokenFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    System.out.println("함수 받은 쿠키들: " + Arrays.toString(cookies));
    if (cookies == null) return null;

    for (Cookie cookie : cookies) {
      if ("refreshToken".equals(cookie.getName())) {
        return cookie.getValue();
      }
    }
    return null;
  }

}
