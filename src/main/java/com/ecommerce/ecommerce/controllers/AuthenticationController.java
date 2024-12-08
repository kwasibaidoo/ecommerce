package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.data_transfer_objects.RegistrationDTO;
import com.ecommerce.ecommerce.services.AppUserService;
import com.ecommerce.ecommerce.utils.ValidationResponse;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthenticationController {

    @Autowired
    private AppUserService appUserService;
    
    // create a custom validation errors class
    @PostMapping("/register")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        if(result.hasErrors()) {
            result.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            ValidationResponse validationResponse = new ValidationResponse(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }
        else{
            appUserService.registerUser(registrationDTO);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
    }
    
}
