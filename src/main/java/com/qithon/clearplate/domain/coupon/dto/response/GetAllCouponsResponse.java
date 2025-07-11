package com.qithon.clearplate.domain.coupon.dto.response;

import com.qithon.clearplate.domain.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetAllCouponsResponse {

  private String couponTitle;
  private String couponCode;
  private String couponDescription;
  private Integer couponDiscountValue; // 할인 금액

  public static GetAllCouponsResponse from(Coupon coupon) {
    return GetAllCouponsResponse.builder()
        .couponTitle(coupon.getCouponTitle())
        .couponCode(coupon.getCouponCode())
        .couponDescription(coupon.getCouponDescription())
        .couponDiscountValue(coupon.getCouponDiscountValue())
        .build();
  }


}
