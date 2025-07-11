package com.qithon.clearplate.domain.food.repository;

import com.qithon.clearplate.domain.food.entity.Food;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

  Optional<Food> findTopByUserIdOrderByBeforeEatTimeDesc(Long userId);

  List<Food> findAllByUserIdOrderByBeforeEatTimeDesc(Long userId);
}
