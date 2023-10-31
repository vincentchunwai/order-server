package com.dev.apigateway.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OrderCreateRequestDTO {
  
  private char messageHeader;
  private String clOrdID;
  private char side;
  private LocalDateTime transactTime;
  private Instrument instrument;
  private Double orderQty;
  private char ordType;
  private String checkSum;
}
