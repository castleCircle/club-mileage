package me.wony.clubmileage.dto.response.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

  private boolean succeeded;
  private String message;
  private T payload;

  public static <T> ApiResponse<T> of(String message, T payload) {
    var apiResponse = new ApiResponse<T>();
    apiResponse.succeeded = true;
    apiResponse.message = message;
    apiResponse.payload = payload;

    return apiResponse;
  }

  public static ApiResponse<Void> of(String message) {
    return of(message, null);
  }

  public static <T> ApiResponse<T> payload(T payload) {
    return of(null, payload);
  }

  public static ApiResponse<Void> ofError(Exception exception) {
    return ofError(exception, null);
  }

  public static <T> ApiResponse<T> ofError(Exception exception, T payload) {
    var apiResponse = new ApiResponse<T>();
    apiResponse.succeeded = false;
    apiResponse.message = exception.getMessage();
    apiResponse.payload = payload;

    return apiResponse;
  }

  public static ApiResponse<Void> succeed() {
    return of(null);
  }

}