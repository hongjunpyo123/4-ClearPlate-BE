package com.qithon.clearplate.domain.user.dto.response;

import com.qithon.clearplate.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponse {

  private Long id;
  private String socialEmail;
  private String nickname;
  private Long cpPoint;

  public static UserResponse from(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .socialEmail(user.getSocialEmail())
        .nickname(user.getNickname())
        .cpPoint(user.getCpPoint())
        .build();
  }


}
