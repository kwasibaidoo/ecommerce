package com.ecommerce.ecommerce.utils;

import com.ecommerce.ecommerce.data_transfer_objects.RegistrationDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, RegistrationDTO> {

    @Override
    public boolean isValid(RegistrationDTO registrationDTO, ConstraintValidatorContext context) {
        String password = registrationDTO.getPassword();
        String confirm_password = registrationDTO.getConfirm_password();

        if(password == null || !password.equals(confirm_password)) {
            return false;
        }
        
        return true;
    }

}
