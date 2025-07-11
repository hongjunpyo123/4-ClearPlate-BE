package com.qithon.clearplate.domain.CLPRestaurant.service;

import com.qithon.clearplate.domain.CLPRestaurant.dto.request.CLPRestaurantRegisterRequest;
import com.qithon.clearplate.domain.CLPRestaurant.dto.response.CLPRestaurantRegisterResponse;
import com.qithon.clearplate.domain.CLPRestaurant.entity.CLPRestaurant;
import com.qithon.clearplate.domain.CLPRestaurant.repository.CLPRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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



}
