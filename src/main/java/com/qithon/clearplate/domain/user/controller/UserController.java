package com.qithon.clearplate.domain.user.controller;

import com.amazonaws.Response;
import com.qithon.clearplate.domain.user.dto.response.UserResponse;
import com.qithon.clearplate.domain.user.entity.User;
import com.qithon.clearplate.domain.user.service.UserService;
import com.qithon.clearplate.global.common.dto.response.ResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<?> getLoginUser(HttpSession httpSession) {
    try {
      UserResponse userResponse = userService.getLoginUser(httpSession);
      return ResponseEntity.ok().body(ResponseDTO.response(userResponse));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(
          ResponseDTO.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
    }
  }

}
