package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        return User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getEmail())
                .build();
    }

}