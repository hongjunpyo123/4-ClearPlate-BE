package com.qithon.clearplate.domain.point.service;

import com.qithon.clearplate.domain.point.dto.request.AddPointRequest;
import com.qithon.clearplate.domain.point.dto.request.SubPointRequest;
import com.qithon.clearplate.domain.user.dto.response.UserResponse;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import com.qithon.clearplate.global.security.config.ServletLogin;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

  private final UserRepository userRepository;
  private final ServletLogin servletLogin;

  public UserResponse subtractPoint(SubPointRequest requestDTO, HttpServletRequest request) {
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);
    System.out.println("추출된 userId = " + userId);

    if(userRepository.findById(userId).isPresent()) {
      User user = userRepository.findById(userId).orElse(null);

      if(requestDTO.getSubtractPoint() <= user.getCpPoint()) {
        User updateUser = User.builder()
            .id(user.getId())
            .socialEmail(user.getSocialEmail())
            .nickname(user.getNickname())
            .cpPoint(user.getCpPoint() - requestDTO.getSubtractPoint())
            .build();

        return UserResponse.from(userRepository.save(updateUser));
      } else {
        throw new RuntimeException("포인트가 부족합니다.");
      }

    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }

  }

  public UserResponse addPoint(AddPointRequest requestDTO, HttpServletRequest request) {
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);
    System.out.println("추출된 userId = " + userId);

    if(userRepository.findById(userId).isPresent()) {
      User user = userRepository.findById(userId).orElse(null);

        User updateUser = User.builder()
            .id(user.getId())
            .socialEmail(user.getSocialEmail())
            .nickname(user.getNickname())
            .cpPoint(user.getCpPoint() + requestDTO.getAddPoint())
            .build();

        return UserResponse.from(userRepository.save(updateUser));

    } else {
      throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }
  }

}
