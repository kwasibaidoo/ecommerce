package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.data_transfer_objects.RegistrationDTO;
import com.ecommerce.ecommerce.services.AppUserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthenticationController {

    @Autowired
    private AppUserService appUserService;
    
    // create a custom validation errors class
    @PostMapping("/register")
    public ResponseEntity<String> postMethodName(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) {
        
        if(result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }
        else{
            appUserService.registerUser(registrationDTO);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
    }
    
}
