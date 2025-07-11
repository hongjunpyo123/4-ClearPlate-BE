package com.qithon.clearplate.domain.CLPRestaurant.controller;

import com.qithon.clearplate.domain.CLPRestaurant.dto.request.CLPLocationVerifyRequest;
import com.qithon.clearplate.domain.CLPRestaurant.dto.request.CLPRestaurantRegisterRequest;
import com.qithon.clearplate.domain.CLPRestaurant.dto.response.CLPRestaurantRegisterResponse;
import com.qithon.clearplate.domain.CLPRestaurant.service.CLPService;
import com.qithon.clearplate.global.common.dto.response.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clp")
public class CLPController {

  private final CLPService clpService;

  @PostMapping
  public ResponseEntity<?> registerRestaurant(@RequestBody List<CLPRestaurantRegisterRequest> requestDTOs) {
    try {
      List<CLPRestaurantRegisterResponse> response = clpService.registerRestaurantList(requestDTOs);
      return ResponseEntity.ok(ResponseDTO.response(response));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body("매장 등록에 실패하였습니다.: " + e.getMessage());
    }
  }

  @PostMapping("/location/verify")
  public ResponseEntity<?> verifyLocation(@RequestBody CLPLocationVerifyRequest requestDTO) {
    return null;
  }


}
