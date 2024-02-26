package com.ebanking.mapper;

import com.ebanking.dto.RegistrationDto;
import com.ebanking.models.UserEntity;

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
