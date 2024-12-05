package com.ecommerce.ecommerce.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "Password do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
