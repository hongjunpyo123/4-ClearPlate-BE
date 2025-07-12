package com.qithon.clearplate.domain.coupon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterCouponResponse {

  private String couponTitle;

  public static RegisterCouponResponse of(String couponTitle) {
    return RegisterCouponResponse.builder()
        .couponTitle(couponTitle)
        .build();
  }
}
