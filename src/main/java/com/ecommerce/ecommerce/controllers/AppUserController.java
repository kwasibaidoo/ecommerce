package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.facades.IAuthenticationFacade;
import com.ecommerce.ecommerce.models.AppUser;
import com.ecommerce.ecommerce.services.AppUserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;
    
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        Authentication authentication = authenticationFacade.getAuthentication();
        String email = authentication.getName();

        Optional<AppUser> appUser = appUserService.getUserByEmail(email);
        if(appUser.isPresent()) {
            return ResponseEntity.ok(appUser.get());
        }
        else{
            return ResponseEntity.status(404).body("User profile not found");
        }
    }
    
    
}
