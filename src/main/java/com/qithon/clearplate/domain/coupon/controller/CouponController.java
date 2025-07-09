package com.qithon.clearplate.domain.coupon.controller;

import com.qithon.clearplate.domain.coupon.dto.request.RegisterCouponRequest;
import com.qithon.clearplate.domain.coupon.dto.response.RegisterCouponResponse;
import com.qithon.clearplate.domain.coupon.service.RegisterCouponService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

  private final RegisterCouponService registerCouponService;

  @PostMapping
  public RegisterCouponResponse registerCoupon(RegisterCouponRequest requestDTO) {
    try {
      registerCouponService.registerCoupon(requestDTO);
    } catch (RuntimeException e) {

    }
  }




}
