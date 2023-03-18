package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.UserDto;
import com.example.java.test.junior.developer.dto.UserRequestDto;
import com.example.java.test.junior.developer.model.User;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserMapper {
    public User toModel(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public UserDto toDto(UserRequestDto userRequestDto) {
        return UserDto.builder()
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .email(userRequestDto.getEmail())
                .build();
    }

    public UserRepresentation toUserRepresentation(UserRequestDto userRequestDto) {
        var user = new UserRepresentation();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setEnabled(true);

        var credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(userRequestDto.getPassword());
        credential.setTemporary(false);
        user.setCredentials(Arrays.asList(credential));
        return user;
    }
}