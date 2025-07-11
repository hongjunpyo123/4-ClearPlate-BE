package com.qithon.clearplate.infra.gemini;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GeminiClient {

  private final WebClient webClient;
  private final ObjectMapper objectMapper;

  @Value("${gemini.api-key}")
  private String apiKey;

  private static final String PATH = "/v1beta/models/gemini-2.5-flash:generateContent";

  public Mono<String> analyzeImages(String base64Img1, String base64Img2) {
    Map<String, Object> promptPart = Map.of("text",
        """

   """);
    Map<String, Object> beforeImagePart = Map.of(
        "inline_data", Map.of(
            "mime_type", "image/jpeg",
            "data", base64Img1
        )
    );

    Map<String, Object> afterImagePart = Map.of(
        "inline_data", Map.of(
            "mime_type", "image/jpeg",
            "data", base64Img2
        )
    );

    Map<String, Object> content = Map.of(
        "parts", List.of(promptPart, beforeImagePart, afterImagePart)
    );

    Map<String, Object> requestBody = Map.of(
        "contents", List.of(content)
    );

    return webClient.post()
        .uri(uriBuilder -> uriBuilder
            .path(PATH)
            .queryParam("key", apiKey)
            .build())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(String.class)
        .map(response -> {
          try {
            JsonNode root = objectMapper.readTree(response);
            String result = root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();


            if (!result.matches(".*\\d{1,3}\\s*%.*")) {
              return "이미지가 비교 가능한 동일한 음식이 아니거나 퍼센트로 응답하지 못했습니다. 다른 사진을 넣어주세요.";
            }

            return result;

          } catch (Exception e) {
            throw new RuntimeException("Gemini API 응답 파싱 실패", e);
          }
        });
  }
}