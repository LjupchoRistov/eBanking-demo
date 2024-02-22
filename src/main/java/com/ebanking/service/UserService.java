package com.ebanking.service;

import com.ebanking.models.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(com.example.racepulse.dto.RegistrationDto registrationDto);
    void saveUser(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
    UserEntity findById(Long id);
    List<UserEntity> findAll();
}
