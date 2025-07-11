package com.qithon.clearplate.global.common.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * 공통 응답 DTO 클래스 입니다.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime timestamp = LocalDateTime.now();
  private HttpStatus status;
  private String message;
  private T data;

  public static <T> ResponseDTO<T> response(HttpStatus status, String message, T data) {
    ResponseDTO<T> responseDTO = new ResponseDTO<>();
    responseDTO.setStatus(status);
    responseDTO.setMessage(message);
    responseDTO.setData(data);
    return responseDTO;
  }

  public static <T> ResponseDTO<T> response(T data) {
    ResponseDTO<T> responseDTO = new ResponseDTO<>();
    responseDTO.setData(data);
    return responseDTO;
  }

}
