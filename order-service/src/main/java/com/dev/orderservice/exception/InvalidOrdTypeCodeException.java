package com.dev.orderservice.exception;

public class InvalidOrdTypeCodeException extends RuntimeException{
  
  public InvalidOrdTypeCodeException(String message){
    super(message);
  }
}
