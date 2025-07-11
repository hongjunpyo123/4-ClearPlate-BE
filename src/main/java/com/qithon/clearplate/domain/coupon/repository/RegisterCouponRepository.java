package com.qithon.clearplate.domain.coupon.repository;

import com.qithon.clearplate.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterCouponRepository extends JpaRepository<Coupon, Long> {

}
