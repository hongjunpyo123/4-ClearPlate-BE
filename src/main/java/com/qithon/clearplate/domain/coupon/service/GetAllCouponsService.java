package com.qithon.clearplate.domain.coupon.service;

import com.qithon.clearplate.domain.coupon.dto.response.GetAllCouponsResponse;
import com.qithon.clearplate.domain.coupon.entity.Coupon;
import com.qithon.clearplate.domain.coupon.repository.RegisterCouponRepository;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllCouponsService {

  private final RegisterCouponRepository registerCouponRepository;
  private final UserRepository userRepository;

  public List<GetAllCouponsResponse> getAllCouponsByUserId(Long userId) {
    User user = userRepository.findById(userId).orElseThrow( () -> new RuntimeException("사용자를 찾을 수 없습니다."));
    List<Coupon> coupons = user.getCoupons();
    List<GetAllCouponsResponse> responseList = coupons.stream()
        .map(coupon -> GetAllCouponsResponse.from(coupon))
        .toList();

    return responseList;
  }

}
