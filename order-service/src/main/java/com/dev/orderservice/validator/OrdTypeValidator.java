package com.dev.orderservice.validator;

import java.util.Arrays;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OrdTypeValidator implements ConstraintValidator<OrdTypeValidation, Character>{
  private String[] allowedOrdTypeValues;

  @Override
  public void initialize(OrdTypeValidation constraintAnnotation){
    allowedOrdTypeValues = constraintAnnotation.values();
  }

  @Override
  public boolean isValid(Character value, ConstraintValidatorContext context){
    if(Objects.isNull(value))
      return false;
    
    return Arrays.asList(allowedOrdTypeValues).contains(value.toString());
  }
}
