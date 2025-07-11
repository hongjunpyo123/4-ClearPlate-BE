package com.qithon.clearplate.infra.Aws.awsTestContoller;

import com.qithon.clearplate.infra.Aws.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aws")
public class AwsController {

  private final S3Service s3Service;

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String uploadImage(
      @RequestPart("file") MultipartFile file,
      @RequestParam(defaultValue = "test") String folder
  ) {
    return s3Service.uploadFile(folder, file);
  }
}