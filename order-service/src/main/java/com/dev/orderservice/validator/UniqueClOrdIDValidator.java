package com.dev.orderservice.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.dev.orderservice.service.OrderService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueClOrdIDValidator implements ConstraintValidator<UniqueClOrdID, String>{
  
  @Autowired
  private OrderService orderService;

  @Override
  public void initialize(UniqueClOrdID constraintAnnotation){

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return Objects.nonNull(value) && orderService.findOrderRecordByClOrdID(value).isEmpty();
  }
}
