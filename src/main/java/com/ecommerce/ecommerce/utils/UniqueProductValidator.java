package com.ecommerce.ecommerce.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.ecommerce.repositories.ProductRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueProductValidator implements ConstraintValidator<UniqueProduct, String> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !productRepository.existsByName(name);
    }

}
