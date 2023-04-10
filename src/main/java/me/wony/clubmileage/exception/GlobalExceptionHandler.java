package me.wony.clubmileage.exception;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wony.clubmileage.dto.response.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler({ReviewValidationException.class,UnsupportedOperationException.class})
  public ResponseEntity<ApiResponse<Void>> handleReviewValidationException(
      final HttpServletRequest request,
      final ResourceNotFoundException ex) {

    log.error("ReviewValidationException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ApiResponse.ofError(ex));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleNotFoundException(
      final HttpServletRequest request,
      final ResourceNotFoundException ex) {

    log.error("ResourceNotFoundException {}\n", request.getRequestURI(), ex);

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ApiResponse.ofError(ex));
  }

  //...추가

}
