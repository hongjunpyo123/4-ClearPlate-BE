package com.qithon.clearplate.domain.food.controller;

import com.qithon.clearplate.domain.food.dto.response.FoodResponseDto;
import com.qithon.clearplate.domain.food.service.FoodService;
import com.qithon.clearplate.global.security.config.ServletLogin;
import com.qithon.clearplate.global.security.core.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Food", description = "음식 관련 API")
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {

  private final FoodService foodService;
  private final HttpServletRequest request;
  private final ServletLogin servletLogin;

  @Operation(summary = "음식 추가", description = "사용자가 먹기 전 사진을 추가합니다.")
  @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> addBeforeFood(@RequestPart("beforeimg") MultipartFile beforeImg, HttpServletRequest request) {
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);

   // Long userId=customUserDetails.getId();
    String imageUrl = foodService.addBeforeFood(beforeImg, userId);
    return ResponseEntity.ok("음식이 추가되었습니다. 이미지 URL: " + imageUrl);
  }
  @Operation(summary = "음식 분석", description = "사용자가 먹은 후 사진을 추가하고 전의 사진과 비교하여 분석합니다.")
  @PostMapping(value = "/verify", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<String> verifyFoods(@RequestPart("afterimg") MultipartFile afterImg, HttpServletRequest request) {
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);
    String result = foodService.vertifyFoods(afterImg, userId);
    return ResponseEntity.ok(result);
  }
  @Operation(summary = "음식 조회", description = "사용자가 먹은 음식 목록을 조회합니다.")
  @GetMapping("/list")
  public ResponseEntity<List<FoodResponseDto>> getFoodList(HttpServletRequest request) {
    Long userId = servletLogin.extractUserIdFromRefreshToken(request);
    return ResponseEntity.ok(foodService.getFoodList(userId));
  }



}
