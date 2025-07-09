package com.qithon.clearplate.domain.coupon.entity;

import com.qithon.clearplate.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {

  @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  private String couponTitle;


  @Column(nullable = false, unique = true)
  private String couponCode;

  @Column(length = 100)
  private String couponDescription;

  private Integer couponPrice;

  private Integer couponDiscountValue; // 할인 금액

  @CreatedDate
  private LocalDateTime createdAt;    // 등록일

  private LocalDateTime expiresAt;    // 만료일

  private LocalDateTime usedAt;       // 사용일 (null이면 미사용)

  @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  private Coupon(String couponTitle, String couponCode, String couponDescription, Integer couponPrice
  , Integer couponDiscountValue, LocalDateTime expiresAt, User user) {
    this.couponTitle = couponTitle;
    this.couponCode = couponCode;
    this.couponDescription = couponDescription;
    this.couponPrice = couponPrice;
    this.couponDiscountValue = couponDiscountValue;
    this.user = user;
    this.expiresAt = expiresAt;
    this.usedAt = null;
  }

  /**
   * 쿠폰을 생성하는 정적 팩토리 메서드입니다.
   * @param couponPrice 쿠폰 가격
   * @param couponDiscountValue 쿠폰 할인 금액
   * @param expiresAt 쿠폰 만료일
   * @param couponDescription 쿠폰 설명
   * @param user 쿠폰을 소유한 사용자
   * @return 생성된 Coupon 객체
   */
  public static Coupon createCouponOf(String couponTitle, Integer couponPrice, Integer couponDiscountValue,
      LocalDateTime expiresAt, String couponDescription, User user) {
    return Coupon.builder()
        .couponCode(getCouponCode())
        .couponDescription(couponDescription)
        .couponPrice(couponPrice)
        .couponDiscountValue(couponDiscountValue)
        .expiresAt(expiresAt)
        .user(user)
        .build();
  }


  /**
   *  쿠폰 코드를 생성하는 메서드입니다
   * @return 16자리 랜덤 쿠폰 코드 생성
   */
  private static String getCouponCode() {
      String characters = "0123456789abcdefghijklmnopqrstuvwxyz";
      SecureRandom random = new SecureRandom();
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < 16; i++) {
        sb.append(characters.charAt(random.nextInt(characters.length())));
      }

      return sb.toString();
  }


}
