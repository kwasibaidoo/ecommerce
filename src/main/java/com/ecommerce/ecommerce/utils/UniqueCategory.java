package com.ecommerce.ecommerce.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Constraint(validatedBy = UniqueCategoryValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCategory {
    String message() default "Category name is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
