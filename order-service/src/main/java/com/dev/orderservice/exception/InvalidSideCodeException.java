package com.dev.orderservice.exception;

public class InvalidSideCodeException extends RuntimeException{
  
  public InvalidSideCodeException(String message){
    super(message);
  }
}
