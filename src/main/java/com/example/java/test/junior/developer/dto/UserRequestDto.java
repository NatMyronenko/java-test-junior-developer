package com.example.java.test.junior.developer.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Value
@RequiredArgsConstructor
public class UserRequestDto {
    @NotEmpty
    @Pattern(regexp = "[a-zA-Zа-яА-Яіїє]+")
    @NonNull String firstName;

    @Pattern(regexp = "[a-zA-Zа-яА-Яіїє]+")
    @NonNull String lastName;

    @Email(regexp = "[\\S]+@[a-z0-9.\\-]+\\.[a-z]{2,6}", message = "Email should be valid")
    @NonNull String email;

    @NotEmpty
    @NonNull String password;
}