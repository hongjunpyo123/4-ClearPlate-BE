package com.qithon.clearplate.domain.CLPRestaurant.entity;

import jakarta.persistence.Embeddable;
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

}
