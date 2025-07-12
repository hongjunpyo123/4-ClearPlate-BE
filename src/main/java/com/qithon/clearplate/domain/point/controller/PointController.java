package com.qithon.clearplate.domain.point.controller;


import com.qithon.clearplate.domain.point.dto.request.AddPointRequest;
import com.qithon.clearplate.domain.point.dto.request.SubPointRequest;
import com.qithon.clearplate.domain.point.service.PointService;
import com.qithon.clearplate.domain.user.dto.response.UserResponse;
import com.qithon.clearplate.global.common.dto.response.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

  private final PointService pointService;

  @PostMapping("/subtract")
  public ResponseEntity<?> subtractPoint(@RequestBody SubPointRequest requestDTO, HttpServletRequest request) {
    try {
      UserResponse response = pointService.subtractPoint(requestDTO, request);
      return ResponseEntity.ok(
          ResponseDTO.response(HttpStatus.OK, "포인트 차감 성공", response));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(
          ResponseDTO.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
    }
  }

  @PostMapping("/add")
  public ResponseEntity<?> addPoint(@RequestBody AddPointRequest requestDTO, HttpServletRequest request) {
    try {
      UserResponse response = pointService.addPoint(requestDTO, request);
      return ResponseEntity.ok(
          ResponseDTO.response(HttpStatus.OK, "포인트 추가 성공", response));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(
          ResponseDTO.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
    }
  }


}
