package com.qithon.clearplate.domain.CLPrestaurant.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CLPRestaurantRegisterRequest {

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

}
