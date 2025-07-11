package com.qithon.clearplate.domain.CLPrestaurant.service;

import com.qithon.clearplate.domain.CLPrestaurant.dto.request.CLPRestaurantRegisterRequest;
import com.qithon.clearplate.domain.CLPrestaurant.dto.response.CLPRestaurantRegisterResponse;
import com.qithon.clearplate.domain.CLPrestaurant.entity.CLPRestaurant;
import com.qithon.clearplate.domain.CLPrestaurant.repository.CLPRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Service
@ToString
@RequiredArgsConstructor
public class CLPService {

  private final CLPRepository clpRepository;

  public List<CLPRestaurantRegisterResponse> registerRestaurantList(
      List<CLPRestaurantRegisterRequest> requestDTOs) {

    clpRepository.saveAll(requestDTOs.stream()
        .map(requestDTO -> CLPRestaurant.from(requestDTO))
        .toList());
    return requestDTOs.stream()
        .map(requestDTO -> CLPRestaurantRegisterResponse.from(requestDTO))
        .toList();
  }

  public List<CLPRestaurant> getAllRestaurants() throws RuntimeException {
    List<CLPRestaurant> clpRestaurants = clpRepository.findAll();

    return clpRestaurants;
  }



}
