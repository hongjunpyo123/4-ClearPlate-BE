package com.qithon.clearplate.domain.CLPRestaurant.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
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

  @JsonProperty("category_group_code")
  private String categoryGroupCode;

  @JsonProperty("category_group_name")
  private String categoryGroupName;

  @JsonProperty("category_name")
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
