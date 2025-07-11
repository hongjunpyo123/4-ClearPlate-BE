package com.qithon.clearplate.domain.user.service;

import com.qithon.clearplate.domain.user.dto.response.UserResponse;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponse getLoginUser(HttpSession httpSession) {
    Long userId = (Long) httpSession.getAttribute("userId");
    if (userRepository.findById(userId).isPresent()) {
      User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
      return UserResponse.from(userRepository.findById(userId).get());
    } else {
      throw new RuntimeException("로그인된 유저가 없습니다.");
    }
  }

}
