package com.ecommerce.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.data_transfer_objects.RegistrationDTO;
import com.ecommerce.ecommerce.models.AppUser;
import com.ecommerce.ecommerce.repositories.AppUserRepository;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public AppUser registerUser(RegistrationDTO registrationDTO) {
        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        AppUser appUser = new AppUser(registrationDTO.getName(), registrationDTO.getEmail(), encodedPassword, "admin");
        return appUserRepository.save(appUser);
    }
    
}
