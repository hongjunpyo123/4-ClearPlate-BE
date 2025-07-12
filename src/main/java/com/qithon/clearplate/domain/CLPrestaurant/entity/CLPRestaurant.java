package com.qithon.clearplate.domain.CLPrestaurant.entity;

import com.qithon.clearplate.domain.CLPrestaurant.dto.request.CLPRestaurantRegisterRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CLPRestaurant {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String addressName;

  @Embedded
  private CLPCategory clpCategory;

  private String distance;

  private String restaurantId;

  private String phone;

  private String placeName;

  private String placeUrl;

  private String roadAddressName;

  private String x;

  private String y;

  @Column(length = 1000)
  private String imageUrl;

  private String subtitle;

  @Builder
  private CLPRestaurant(String addressName, CLPCategory clpCategory,
      String distance, String restaurantId, String phone, String placeName,
      String placeUrl, String roadAddressName, String x, String y, String imageUrl,String subtitle) {
    this.addressName = addressName;
    this.clpCategory = clpCategory;
    this.distance = distance;
    this.restaurantId = restaurantId;
    this.phone = phone;
    this.placeName = placeName;
    this.placeUrl = placeUrl;
    this.roadAddressName = roadAddressName;
    this.x = x;
    this.y = y;
    this.imageUrl = imageUrl;
    this.subtitle = subtitle;
  }

  public static CLPRestaurant from(CLPRestaurantRegisterRequest requestDTO) {
    CLPCategory clpCategory = CLPCategory.of(
        requestDTO.getCategoryGroupCode(),
        requestDTO.getCategoryGroupName(),
        requestDTO.getCategoryName());

    return CLPRestaurant.builder()
        .addressName(requestDTO.getAddressName())
        .clpCategory(clpCategory)
        .distance(requestDTO.getDistance())
        .restaurantId(requestDTO.getRestaurantId())
        .phone(requestDTO.getPhone())
        .placeName(requestDTO.getPlaceName())
        .placeUrl(requestDTO.getPlaceUrl())
        .roadAddressName(requestDTO.getRoadAddressName())
        .x(requestDTO.getX())
        .y(requestDTO.getY())
        .imageUrl(requestDTO.getImageUrl())
        .subtitle(requestDTO.getSubtitle())
        .build();
  }

}
