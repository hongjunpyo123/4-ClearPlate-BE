package com.qithon.clearplate.domain.coupon.controller;

import com.qithon.clearplate.domain.coupon.dto.request.RegisterCouponRequest;
import com.qithon.clearplate.domain.coupon.dto.response.RegisterCouponResponse;
import com.qithon.clearplate.domain.coupon.repository.RegisterCouponRepository;
import com.qithon.clearplate.domain.coupon.service.GetAllCouponsService;
import com.qithon.clearplate.domain.coupon.service.RegisterCouponService;
import com.qithon.clearplate.global.common.dto.response.ResponseDTO;
import com.qithon.clearplate.global.exception.ErrorCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

  private final RegisterCouponService registerCouponService;
  private final GetAllCouponsService getAllCouponsService;





  @PostMapping
  public ResponseEntity<?> registerCoupon(@RequestBody RegisterCouponRequest requestDTO) {
    try {
      RegisterCouponResponse response = registerCouponService.registerCoupon(requestDTO);
      return ResponseEntity.ok().body(ResponseDTO.response(response));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest()
          .body(ResponseDTO.response(HttpStatus.BAD_REQUEST, "쿠폰 등록에 실패하였습니다.", null));
    }
  }

  @GetMapping("/all/{userId}")
  public ResponseEntity<?> getAllCouponsByUserId(@PathVariable Long userId) {
    try {
      return ResponseEntity.ok().body(ResponseDTO.response(getAllCouponsService.getAllCouponsByUserId(userId)));
    } catch (RuntimeException e) {
      return ResponseEntity.ok().body(ResponseDTO.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
    }
  }





}
