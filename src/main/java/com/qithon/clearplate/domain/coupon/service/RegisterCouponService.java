package com.qithon.clearplate.domain.coupon.service;

import com.qithon.clearplate.domain.coupon.dto.request.RegisterCouponRequest;
import com.qithon.clearplate.domain.coupon.dto.response.RegisterCouponResponse;
import com.qithon.clearplate.domain.coupon.entity.Coupon;
import com.qithon.clearplate.domain.coupon.repository.RegisterCouponRepository;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import com.qithon.clearplate.global.security.config.ServletLogin;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterCouponService {

  private final RegisterCouponRepository registerCouponRepository;
  private final ServletLogin servletLogin;
  private final UserRepository userRepository;

  public RegisterCouponResponse registerCoupon(RegisterCouponRequest requestDTO, HttpServletRequest request) throws RuntimeException{
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);
    System.out.println("userId = " + userId);

    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      log.error("사용자를 찾을 수 없습니다. userId: {}", userId);
      throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }

    Coupon coupon = Coupon.createCouponOf(requestDTO.getCouponTitle(), requestDTO.getCouponDiscountValue(),
        requestDTO.getExpiresAt(), requestDTO.getCouponDescription(), user);
    registerCouponRepository.save(coupon);
    return RegisterCouponResponse.of(coupon.getCouponTitle());
  }

}
