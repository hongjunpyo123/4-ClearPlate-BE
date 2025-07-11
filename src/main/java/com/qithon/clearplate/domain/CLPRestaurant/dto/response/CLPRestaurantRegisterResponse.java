package com.qithon.clearplate.domain.CLPRestaurant.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qithon.clearplate.domain.CLPRestaurant.dto.request.CLPRestaurantRegisterRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CLPRestaurantRegisterResponse {

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

  /**
   * CLPRestaurantRegisterRequest를 기반으로 CLPRestaurantRegisterResponse를 생성합니다.
   *
   * @param requestDTO CLPRestaurantRegisterRequest 객체
   * @param distance 매장과 사용자의 거리 데이터
   * @return CLPRestaurantRegisterResponse 객체
   */
  public static CLPRestaurantRegisterResponse of(CLPRestaurantRegisterRequest requestDTO, String distance) {
    return CLPRestaurantRegisterResponse.builder()
        .addressName(requestDTO.getAddressName())
        .categoryGroupCode(requestDTO.getCategoryGroupCode())
        .categoryGroupName(requestDTO.getCategoryGroupName())
        .categoryName(requestDTO.getCategoryName())
        .distance(distance)
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

  public static CLPRestaurantRegisterResponse from(CLPRestaurantRegisterRequest requestDTO) {
    return CLPRestaurantRegisterResponse.builder()
        .addressName(requestDTO.getAddressName())
        .categoryGroupCode(requestDTO.getCategoryGroupCode())
        .categoryGroupName(requestDTO.getCategoryGroupName())
        .categoryName(requestDTO.getCategoryName())
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
