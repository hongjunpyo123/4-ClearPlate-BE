package com.qithon.clearplate.domain.CLPrestaurant.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qithon.clearplate.domain.CLPrestaurant.entity.CLPRestaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CLPLocationVerifyResponse {

  @JsonProperty("address_name")
  private String addressName;

  @JsonProperty("category_group_code")
  private String categoryGroupCode;

  @JsonProperty("category_group_name")
  private String categoryGroupName;

  @JsonProperty("category_name")
  private String categoryName;

  private String distance;

  @JsonProperty("id")
  private String restaurantId;

  private String phone;

  @JsonProperty("place_name")
  private String placeName;

  @JsonProperty("place_url")
  private String placeUrl;

  @JsonProperty("road_address_name")
  private String roadAddressName;

  private String x;

  private String y;

  @JsonProperty("image_url")
  private String imageUrl;

  private String subtitle;

  public static CLPLocationVerifyResponse from(CLPRestaurant restaurant) {
    return CLPLocationVerifyResponse.builder()
        .addressName(restaurant.getAddressName())
        .categoryGroupCode(restaurant.getClpCategory().getCategoryGroupCode())
        .categoryGroupName(restaurant.getClpCategory().getCategoryGroupName())
        .categoryName(restaurant.getClpCategory().getCategoryName())
        .distance(restaurant.getDistance())
        .restaurantId(restaurant.getRestaurantId())
        .phone(restaurant.getPhone())
        .placeName(restaurant.getPlaceName())
        .placeUrl(restaurant.getPlaceUrl())
        .roadAddressName(restaurant.getRoadAddressName())
        .x(restaurant.getX())
        .y(restaurant.getY())
        .imageUrl(restaurant.getImageUrl())
        .subtitle(restaurant.getSubtitle())
        .build();
  }



}
