package com.qithon.clearplate.domain.CLPrestaurant.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class CLPLocationVerifyRequest {

  private Double userLatitude;
  private Double userLongitude;

  private String restaurantId;

}
