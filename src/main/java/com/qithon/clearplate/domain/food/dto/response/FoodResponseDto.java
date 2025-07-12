package com.qithon.clearplate.domain.food.dto.response;

import com.qithon.clearplate.domain.food.entity.Food;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodResponseDto {
  private LocalDateTime beforeEatTime;
  private LocalDateTime afterEatTime;
  private String seftPercentage;

  public static FoodResponseDto from(Food food) {
    return new FoodResponseDto(
        food.getBeforeEatTime(),
        food.getAfterEatTime(),
        food.getLeftPercentage()
    );
  }
}
