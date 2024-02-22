package com.example.racepulse.mapper;

import com.example.racepulse.dto.RegistrationDto;
import com.example.racepulse.models.UserEntity;

public class UserMapper {
    public static RegistrationDto mapToRegistrationDto(UserEntity user){
        return RegistrationDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getHashedPassword())
                .build();
    }
}
