package com.qithon.clearplate.domain.CLPrestaurant.repository;

import com.qithon.clearplate.domain.CLPrestaurant.entity.CLPRestaurant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CLPRepository extends JpaRepository<CLPRestaurant, Long> {
  Optional<CLPRestaurant> findByRestaurantId(String restaurantId);

}
