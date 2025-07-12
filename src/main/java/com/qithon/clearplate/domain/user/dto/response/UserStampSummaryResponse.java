package com.qithon.clearplate.domain.user.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserStampSummaryResponse {
  private int stampCount;
  private int maxDisplayCount;
  private List<UserStampResponse> stamps;
}