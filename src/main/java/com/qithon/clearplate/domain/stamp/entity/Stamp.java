package com.qithon.clearplate.domain.stamp.entity;

import com.qithon.clearplate.domain.CLPrestaurant.entity.CLPRestaurant;
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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stamp")
@Entity
public class Stamp {
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Column
  private LocalDateTime createdAt;





  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id", nullable = false)
  private CLPRestaurant clpRestaurant;

  @Builder
  public Stamp(User user, CLPRestaurant clpRestaurant, LocalDateTime createdAt) {
    this.user = user;
    this.clpRestaurant = clpRestaurant;
    this.createdAt = createdAt;
  }


}
