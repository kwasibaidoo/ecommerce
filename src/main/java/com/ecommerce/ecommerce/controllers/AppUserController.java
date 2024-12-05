package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.models.AppUser;
import com.ecommerce.ecommerce.services.AppUserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    
    @GetMapping("/profile")
    public AppUser getProfile(@PathVariable) {
        return appUserService.userProfile(null);
    }
    
    
}
