package com.dev.orderservice.exception;

public class UpdateOrderUnsuccessful extends RuntimeException{
  
  public UpdateOrderUnsuccessful(String message) {
    super(message);
  }
}
