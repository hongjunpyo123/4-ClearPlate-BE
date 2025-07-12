package com.qithon.clearplate.domain.food.service;


import com.qithon.clearplate.domain.food.dto.response.FoodResponseDto;
import com.qithon.clearplate.domain.food.entity.Food;
import com.qithon.clearplate.domain.food.repository.FoodRepository;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.repository.UserRepository;
import com.qithon.clearplate.infra.Aws.S3Service;
import com.qithon.clearplate.infra.gemini.GeminiClient;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class FoodService {
  private final FoodRepository foodRepository;
  private final UserRepository userRepository;
  private final S3Service s3Service;
  private final GeminiClient geminiClient;


  public String addBeforeFood(MultipartFile beforeImg, Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    String imageUrl = s3Service.uploadFile("food/before", beforeImg);

    Food food = Food.builder()
        .foodImageUrl(imageUrl)
        .beforeEatTime(LocalDateTime.now())
        .isVerified(false)
        .user(user)
        .build();

    foodRepository.save(food);

    return imageUrl;
  }

  public String vertifyFoods(MultipartFile afterImg, Long userId) {

    Food beforeFood = foodRepository.findTopByUserIdOrderByBeforeEatTimeDesc(userId)
        .orElseThrow(() -> new IllegalArgumentException("이전 음식 사진이 없습니다."));

    String afterImageUrl = s3Service.uploadFile("food/after", afterImg);

    String beforeBase64 = downloadAndEncodeBase64(beforeFood.getFoodImageUrl());
    String afterBase64 = downloadAndEncodeBase64(afterImageUrl);

    String analyzeResult = geminiClient.analyzeImages(beforeBase64, afterBase64).block();

    String percentage = extractPercentage(analyzeResult);
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    user.addPoint(Long.parseLong(percentage));
    log.info("사용자 {}의 포인트가 {}만큼 증가했습니다.", user.getNickname(), percentage);
    userRepository.save(user);

    beforeFood.updateAfterEatInfo(afterImageUrl, percentage, LocalDateTime.now(), true);

    foodRepository.save(beforeFood);

    return analyzeResult;
  }
  private String downloadAndEncodeBase64(String imageUrl) {
    try (InputStream in = new URL(imageUrl).openStream()) {
      byte[] bytes = in.readAllBytes();
      return Base64.getEncoder().encodeToString(bytes);
    } catch (IOException e) {
      throw new RuntimeException("이미지 다운로드 실패: " + imageUrl, e);
    }
  }


  private String extractPercentage(String analyzeResult) {
    Pattern pattern = Pattern.compile("(\\d{1,3})\\s*%");
    Matcher matcher = pattern.matcher(analyzeResult);
    return matcher.find() ? matcher.group(1) : null;
  }

  public List<FoodResponseDto> getFoodList(Long userId) {
    List<Food> foodList = foodRepository.findAllByUserIdOrderByBeforeEatTimeDesc(userId);
    return foodList.stream()
        .map(FoodResponseDto::from)
        .toList();
  }
}
