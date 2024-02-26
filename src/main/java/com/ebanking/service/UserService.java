package com.ebanking.service;

import com.ebanking.dto.RegistrationDto;
import com.ebanking.models.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    void saveUser(UserEntity user);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
    UserEntity findById(Long id);
    List<UserEntity> findAll();
}
