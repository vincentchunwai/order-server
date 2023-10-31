package com.dev.orderservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(InvalidSideCodeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleInvalidSideCodeException(InvalidSideCodeException ex, WebRequest webRequest){
    return buildErrorMessage(HttpStatus.BAD_REQUEST, "Invalid Side Code", ex.getMessage(), webRequest);
  }

  @ExceptionHandler(InvalidOrdTypeCodeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleInvalidOrdTypeCodeException(InvalidOrdTypeCodeException ex, WebRequest webRequest){
    return buildErrorMessage(HttpStatus.BAD_REQUEST, "Invalid Order Type Code", ex.getMessage(), webRequest);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorMessage handleGlobalException(Exception ex, WebRequest webRequest){
    return buildErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage(), webRequest);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage handleValidationException(MethodArgumentNotValidException ex, WebRequest webRequest) {
    String errorMessage = "Validation failed. Check the details below.";
    BindingResult result = ex.getBindingResult();
    StringBuilder validationErrors = new StringBuilder();
    result.getFieldErrors().forEach(error -> {
      validationErrors.append(error.getDefaultMessage()).append("\n");
    });
    return buildErrorMessage(HttpStatus.BAD_REQUEST, errorMessage, validationErrors.toString(), webRequest);
  }

  @ExceptionHandler(UpdateOrderUnsuccessful.class)
  @ResponseStatus
  public ErrorMessage handleUpdateOrderUnsuccessful(UpdateOrderUnsuccessful ex, WebRequest webRequest){
    return buildErrorMessage(HttpStatus.NOT_ACCEPTABLE, "Unable to update order.", ex.getMessage(), webRequest);
  }

  private ErrorMessage buildErrorMessage(HttpStatus status, String error, String message, WebRequest webRequest) {
    return ErrorMessage.builder()
      .statusCode(status.value())
      .timestamp(LocalDateTime.now())
      .message(error)
      .description(message)
      .path(webRequest.getDescription(false))
      .build();
  }
}
