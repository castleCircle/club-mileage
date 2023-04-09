package me.wony.clubmileage.exception;

public class ReviewValidationException extends RuntimeException{

  public ReviewValidationException(String message) {
    super(message);
  }

  public ReviewValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReviewValidationException(Throwable cause) {
    super(cause);
  }

  protected ReviewValidationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
