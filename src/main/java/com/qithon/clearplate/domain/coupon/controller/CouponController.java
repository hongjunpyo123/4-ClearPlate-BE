package com.qithon.clearplate.domain.coupon.controller;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

  private Integer couponPrice; //쿠폰 가격(교환시 소모되는 포인트임)
  private Integer couponDiscountValue; //쿠폰 할인 금액
  private LocalDateTime expiresAt; //쿠폰 만료일 "expiresAt": "2024-12-31T23:59:59"
  private String couponDescription; //쿠폰 설명


}
