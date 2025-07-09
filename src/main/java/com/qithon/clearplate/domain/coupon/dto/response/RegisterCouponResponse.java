package com.qithon.clearplate.domain.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterCouponResponse {

  private String couponTitle;
  private Integer couponPrice; //쿠폰 가격(교환시 소모되는 포인트임)

  public static RegisterCouponResponse of(String couponTitle, Integer couponPrice) {
    return RegisterCouponResponse.builder()
        .couponTitle(couponTitle)
        .couponPrice(couponPrice)
        .build();
  }
}
