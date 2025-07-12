package com.qithon.clearplate.domain.coupon.dto.request;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCouponRequest {

  private String couponTitle; //쿠폰 제목
  private Integer couponDiscountValue; //쿠폰 할인 금액
  private LocalDateTime expiresAt; //쿠폰 만료일 "expiresAt": "2024-12-31T23:59:59"
  private String couponDescription; //쿠폰 설명


}
