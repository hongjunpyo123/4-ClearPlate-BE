package com.qithon.clearplate.domain.CLPRestaurant.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class CLPCategory {

  private String categoryGroupCode;

  private String categoryGroupName;

  private String categoryName;

  @Builder
  private CLPCategory(String categoryGroupCode, String categoryGroupName, String categoryName) {
    this.categoryGroupCode = categoryGroupCode;
    this.categoryGroupName = categoryGroupName;
    this.categoryName = categoryName;
  }

  public static CLPCategory of(String categoryGroupCode, String categoryGroupName, String categoryName) {
    return CLPCategory.builder()
        .categoryGroupCode(categoryGroupCode)
        .categoryGroupName(categoryGroupName)
        .categoryName(categoryName)
        .build();
  }

}
