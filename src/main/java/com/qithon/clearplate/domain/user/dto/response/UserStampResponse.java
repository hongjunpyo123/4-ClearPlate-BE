package com.qithon.clearplate.domain.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qithon.clearplate.domain.stamp.entity.Stamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserStampResponse {
  private String restaurantId;
  private String restaurantName;
  private int stampCount;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastStampAt;

  public static UserStampResponse of(String restaurantId, String restaurantName, int count, LocalDateTime lastStampAt) {
    return new UserStampResponse(restaurantId, restaurantName, count, lastStampAt);
  }
}
