package com.dev.orderservice.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrdTypeValidator.class)
public @interface OrdTypeValidation {
  String[] values();
  String message() default "Invalid Order Type value";
  Class<?>[] groups() default{};
  Class<? extends Payload>[] payload() default{};
}
