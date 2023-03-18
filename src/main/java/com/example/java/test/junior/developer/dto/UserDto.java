package com.example.java.test.junior.developer.dto;

import lombok.*;

@Value @Builder
@RequiredArgsConstructor
public class UserDto {
    Long id;
    @NonNull String firstName;
    @NonNull String lastName;
    @NonNull String email;
}