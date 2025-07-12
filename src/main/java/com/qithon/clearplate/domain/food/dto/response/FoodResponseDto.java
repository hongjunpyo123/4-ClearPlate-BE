package com.qithon.clearplate.domain.food.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qithon.clearplate.domain.food.entity.Food;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodResponseDto {
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime afterEatTime;
  private String leftPercentage;

  public static FoodResponseDto from(Food food) {
    return new FoodResponseDto(
        food.getAfterEatTime(),
        food.getLeftPercentage()
    );
  }
}
