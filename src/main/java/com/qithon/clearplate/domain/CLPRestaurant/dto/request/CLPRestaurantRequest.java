package com.qithon.clearplate.domain.CLPRestaurant.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CLPRestaurantRequest {

  private String addressName;

  private String categoryGroupCode;

  private String categoryGroupName;

  private String categoryName;

  private String distance;

  @JsonProperty("id")
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
