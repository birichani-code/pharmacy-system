package com.birichani.code.pharmacysystem.service;

import com.birichani.code.pharmacysystem.model.User;
import com.birichani.code.pharmacysystem.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);


}
