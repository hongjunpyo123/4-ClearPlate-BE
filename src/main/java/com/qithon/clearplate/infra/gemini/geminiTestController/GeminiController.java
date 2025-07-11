package com.qithon.clearplate.infra.gemini.geminiTestController;

import com.qithon.clearplate.infra.gemini.GeminiClient;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class GeminiController {

  private final GeminiClient geminiClient;

  @PostMapping(value = "/gemini", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public Mono<String> analyze(
      @RequestPart("img1") MultipartFile img1,
      @RequestPart("img2") MultipartFile img2) {

    return Mono.fromCallable(() -> {
          String base64Img1 = Base64.getEncoder().encodeToString(img1.getBytes());
          String base64Img2 = Base64.getEncoder().encodeToString(img2.getBytes());
          return new String[]{base64Img1, base64Img2};
        })
        .flatMap(images -> geminiClient.analyzeImages(images[0], images[1]));
  }
}