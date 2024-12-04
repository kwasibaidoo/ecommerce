package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.data_transfer_objects.RegistrationDTO;
import com.ecommerce.ecommerce.models.AppUser;
import com.ecommerce.ecommerce.services.AppUserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthenticationController {

    @Autowired
    private AppUserService appUserService;
    
    @PostMapping("/register")
    public ResponseEntity<AppUser> postMethodName(@RequestBody @Valid RegistrationDTO registrationDTO) {
        
        AppUser appUser = appUserService.registerUser(registrationDTO);
        return new ResponseEntity<>(appUser, HttpStatus.CREATED);
    }
    
}
