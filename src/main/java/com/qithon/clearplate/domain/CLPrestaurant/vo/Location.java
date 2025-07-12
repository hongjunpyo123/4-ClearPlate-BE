package com.qithon.clearplate.domain.CLPrestaurant.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Location {

  @Builder
  private Location(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  private Double latitude;
  private Double longitude;

  public static Location of(Double latitude, Double longitude) {
    return Location.builder()
        .latitude(latitude)
        .longitude(longitude)
        .build();
  }

  public static Location of(String latitude, String longitude) {
    return Location.of(Double.parseDouble(latitude), Double.parseDouble(longitude));
  }


  /**
   * 두 위치 간의 거리를 미터 단위로 계산합니다.
   *
   * @param location1 첫 번째 위치
   * @param location2 두 번째 위치
   * @return 두 위치 간의 거리 반환 (미터 단위)
   */
  public static double calculateDistanceInMeters(Location location1, Location location2) {
    // 위도 및 경도를 라디안으로 변환
    double lat1 = Math.toRadians(location1.getLatitude());
    double lon1 = Math.toRadians(location1.getLongitude());
    double lat2 = Math.toRadians(location2.getLatitude());
    double lon2 = Math.toRadians(location2.getLongitude());

    // Haversine 공식을 사용하여 두 지점 간의 거리 계산
    double dlon = lon2 - lon1;
    double dlat = lat2 - lat1;
    double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    // 지구의 반지름 (미터 단위)
    double radius = 6371000.0;

    // 최종 거리 계산
    Double distance = radius * c;
    return Math.round(distance * 10.0) / 10.0;
  }



}
