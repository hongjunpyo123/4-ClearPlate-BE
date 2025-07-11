package com.qithon.clearplate.domain.CLPRestaurant.controller;

import com.qithon.clearplate.domain.CLPRestaurant.dto.request.CLPLocationVerifyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clp")
public class CLPController {

  @PostMapping("/location/verify")
  public ResponseEntity<?> verifyLocation(@RequestBody CLPLocationVerifyRequest requestDTO) {


  }


}
