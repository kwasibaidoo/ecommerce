package com.ecommerce.ecommerce.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.ecommerce.repositories.AppUserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if(email == null) {
            return true;
        }
        return !appUserRepository.existsByEmail(email);
        
    }

}
