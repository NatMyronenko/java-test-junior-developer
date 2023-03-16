package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toModel(UserDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
