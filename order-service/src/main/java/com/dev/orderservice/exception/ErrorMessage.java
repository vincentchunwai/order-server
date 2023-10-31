package com.dev.orderservice.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErrorMessage {
  private int statusCode;
  private LocalDateTime timestamp;
  private String message;
  private String description;
  private String path;
}
