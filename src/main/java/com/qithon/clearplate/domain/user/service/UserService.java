package com.qithon.clearplate.domain.user.service;

import com.qithon.clearplate.domain.CLPrestaurant.entity.CLPRestaurant;
import com.qithon.clearplate.domain.stamp.entity.Stamp;
import com.qithon.clearplate.domain.stamp.repository.StampRepository;
import com.qithon.clearplate.domain.user.dto.response.UserResponse;
import com.qithon.clearplate.domain.user.dto.response.UserStampResponse;
import com.qithon.clearplate.domain.user.dto.response.UserStampSummaryResponse;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import com.qithon.clearplate.global.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final StampRepository stampRepository;

  public UserResponse getLoginUser(HttpServletRequest request) {

    String refreshToken = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("refreshToken".equals(cookie.getName())) {
          refreshToken = cookie.getValue();
          break;
        }
      }
    }

    Long userId = jwtTokenProvider.getUserIdFromToken(refreshToken);


    if (userRepository.findById(userId).isPresent()) {
      User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
      return UserResponse.from(userRepository.findById(userId).get());
    } else {
      throw new RuntimeException("로그인된 유저가 없습니다.");
    }
  }

  public Long getUserPoint(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    return user.getCpPoint();
  }
  public UserStampSummaryResponse getUserStamps(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    List<Stamp> userStamps = stampRepository.findAllByUser(user);  // 정렬은 필요 없어요 (아래에서 그룹 후 정렬)

    // 식당별로 그룹핑
    Map<CLPRestaurant, List<Stamp>> grouped = userStamps.stream()
        .collect(Collectors.groupingBy(Stamp::getClpRestaurant));

    List<UserStampResponse> groupedResponses = grouped.entrySet().stream()
        .map(entry -> {
          CLPRestaurant restaurant = entry.getKey();
          List<Stamp> stamps = entry.getValue();

          String restaurantId = restaurant.getRestaurantId();
          String restaurantName = restaurant.getRoadAddressName();
          int count = stamps.size();
          LocalDateTime latest = stamps.stream()
              .map(Stamp::getCreatedAt)
              .max(LocalDateTime::compareTo)
              .orElse(null);

          return UserStampResponse.of(restaurantId, restaurantName, count, latest);
        })
        .sorted(Comparator.comparing(UserStampResponse::getLastStampAt).reversed()) // 최근 순 정렬
        .limit(10) // 최대 10개
        .toList();

    return new UserStampSummaryResponse(
        userStamps.size(), // 총 스탬프 수 (개별 스탬프 기준)
        10,
        groupedResponses
    );
  }


}
