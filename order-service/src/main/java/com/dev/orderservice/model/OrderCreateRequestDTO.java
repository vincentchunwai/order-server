package com.dev.orderservice.model;

import java.time.LocalDateTime;

import com.dev.orderservice.config.LocalDateTimeSerializer;
import com.dev.orderservice.validator.OrdTypeValidation;
import com.dev.orderservice.validator.SideValidation;
import com.dev.orderservice.validator.UniqueClOrdID;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OrderCreateRequestDTO {
  
  @NotNull(message = "message Header must not be null")
  private char messageHeader;

  @UniqueClOrdID(message = "clOrdID must be unique")
  private String clOrdID;

  @SideValidation(values = {"1", "2", "3", "4", "6", "9", "J", "K"})
  private char side;

  @NotNull(message = "transaction Time must not be null")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime transactTime;

  @NotNull(message = "instrument must not be null")
  private Instrument instrument;

  @NotNull(message = "order quantity must not be null")
  private Double orderQty;

  @OrdTypeValidation(values = {"1", "2", "3", "4", "5", "8"})
  private char ordType;

  private String checkSum;
}
