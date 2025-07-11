package com.qithon.clearplate.domain.food.entity;


import com.qithon.clearplate.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Table
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Column
  private String foodImageUrl;

  @Column
  private LocalDateTime beforeEatTime;

  @Column
  private LocalDateTime afterEatTime;

  @Column
  private String seftPercentage;

  @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
  private Boolean isVerified;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;


  @Builder
  public Food(String foodImageUrl, LocalDateTime beforeEatTime, LocalDateTime afterEatTime,
      String seftPercentage, Boolean isVerified, User user) {
    this.foodImageUrl = foodImageUrl;
    this.beforeEatTime = beforeEatTime;
    this.afterEatTime = afterEatTime;
    this.seftPercentage = seftPercentage;
    this.isVerified = isVerified;
    this.user = user;
  }
  public void updateAfterEatInfo(String afterImageUrl, String seftPercentage, LocalDateTime afterEatTime, Boolean isVerified) {
    this.foodImageUrl = afterImageUrl;
    this.seftPercentage = seftPercentage;
    this.afterEatTime = afterEatTime;
    this.isVerified = isVerified;
  }



}
