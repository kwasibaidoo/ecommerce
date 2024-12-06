package com.ecommerce.ecommerce.utils;


import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.ecommerce.repositories.CategoryRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean isValid(String category, ConstraintValidatorContext context) {
        return !categoryRepository.existsByName(category);
    }

}
