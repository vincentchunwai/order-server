package com.dev.orderservice.validator;

import java.util.Arrays;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SideValidator implements ConstraintValidator<SideValidation, Character>{
  private String[] allowedSideValues;

  @Override
  public void initialize(SideValidation constraintAnnotation){
    allowedSideValues = constraintAnnotation.values();
  }

  @Override
  public boolean isValid(Character value, ConstraintValidatorContext context){
    if(Objects.isNull(value))
      return false;
    
    return Arrays.asList(allowedSideValues).contains(value.toString());
  }
}
