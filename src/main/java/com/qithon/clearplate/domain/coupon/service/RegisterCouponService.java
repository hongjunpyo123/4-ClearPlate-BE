package com.qithon.clearplate.domain.coupon.service;

import com.qithon.clearplate.domain.coupon.dto.request.RegisterCouponRequest;
import com.qithon.clearplate.domain.coupon.dto.response.RegisterCouponResponse;
import com.qithon.clearplate.domain.coupon.entity.Coupon;
import com.qithon.clearplate.domain.coupon.repository.RegisterCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterCouponService {

  private final RegisterCouponRepository registerCouponRepository;

  public RegisterCouponResponse registerCoupon(RegisterCouponRequest requestDTO) throws RuntimeException{
    Coupon coupon = Coupon.from(requestDTO);
    registerCouponRepository.save(coupon);
    return RegisterCouponResponse.of(coupon.getCouponTitle(), coupon.getCouponPrice());
  }

}
