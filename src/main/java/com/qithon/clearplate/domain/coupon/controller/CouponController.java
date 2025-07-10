package com.qithon.clearplate.domain.coupon.controller;

import com.qithon.clearplate.domain.coupon.dto.request.RegisterCouponRequest;
import com.qithon.clearplate.domain.coupon.dto.response.RegisterCouponResponse;
import com.qithon.clearplate.domain.coupon.repository.RegisterCouponRepository;
import com.qithon.clearplate.domain.coupon.service.GetAllCouponsService;
import com.qithon.clearplate.domain.coupon.service.RegisterCouponService;
import com.qithon.clearplate.global.common.dto.response.ResponseDTO;
import com.qithon.clearplate.global.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

  private final RegisterCouponService registerCouponService;
  private final GetAllCouponsService getAllCouponsService;



  @ApiResponse(
      responseCode = "200",
      description = "### ✅ 쿠폰 등록에 성공한 경우",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(
              example = """
                         {
                          "timestamp": "2025-07-11T00:29:01.568699",
                          "data": {
                            "couponTitle": "string",
                            "couponPrice": 0
                          }
                        } 
                        """

          )
      )
  )
  @ApiResponse(
      responseCode = "400",
      description = "--- \n### ❌ 쿠폰 등록에 실패한 경우",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(
              example = """
                  {
                    "timestamp": "2025-07-11T00:30:38.405707",
                    "status": "BAD_REQUEST",
                    "message": "쿠폰 등록에 실패하였습니다."
                  }  
                 """

          )
      )
  )
  @Operation(summary = "쿠폰을 생성하는 api", description = "쿠폰을 생성합니다. (쿠폰 코드 자동 생성)")
  @PostMapping
  public ResponseEntity<?> registerCoupon(@RequestBody RegisterCouponRequest requestDTO) {
    try {
      RegisterCouponResponse response = registerCouponService.registerCoupon(requestDTO);
      return ResponseEntity.ok().body(ResponseDTO.response(response));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest()
          .body(ResponseDTO.response(HttpStatus.BAD_REQUEST, "쿠폰 등록에 실패하였습니다.", null));
    }
  }






  @ApiResponse(
      responseCode = "200",
      description = "### ✅ 쿠폰 조회에 성공한 경우",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(
              example = """
                         {
                           "timestamp": "2025-07-11T00:31:46.180135",
                           "data": [
                             {
                               "couponTitle": "hello world!",
                               "couponCode": "pv1wilcbjkhusj2s",
                               "couponDescription": "hello world",
                               "couponDiscountValue": 123
                             }
                           ]
                         } 
                        """

          )
      )
  )
  @ApiResponse(
      responseCode = "400",
      description = "--- \n### ❌ 쿠폰 등록에 실패한 경우",
      content = @Content(
          mediaType = "application/json",
          schema = @Schema(
              example = """
                  {
                    "timestamp": "2025-07-11T00:33:29.027814",
                    "status": "BAD_REQUEST",
                    "message": "사용자를 찾을 수 없습니다."
                  } 
                 """

          )
      )
  )
  @Operation(summary = "사용자별 쿠폰 전부 조회 api", description = "사용자 id로 해당 사용자가 가진 쿠폰을 전부 조회하는 api입니다.")
  @GetMapping("/all/{userId}")
  public ResponseEntity<?> getAllCouponsByUserId(@PathVariable Long userId) {
    try {
      return ResponseEntity.ok().body(ResponseDTO.response(getAllCouponsService.getAllCouponsByUserId(userId)));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(ResponseDTO.response(HttpStatus.BAD_REQUEST, e.getMessage(), null));
    }
  }





}
