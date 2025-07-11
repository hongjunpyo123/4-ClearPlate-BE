package com.qithon.clearplate.domain.CLPRestaurant.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CLPRestaurant {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String addressName;

  @Embedded
  private CLPCategory CLPCategory;

  private String distance;

  private String restaurantId;

  private String phone;

  private String placeName;

  private String placeUrl;

  private String roadAddressName;

  private String x;

  private String y;

  private String imageUrl;

  private String subtitle;


}
