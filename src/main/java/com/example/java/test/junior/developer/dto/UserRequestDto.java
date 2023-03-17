package com.example.java.test.junior.developer.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class UserRequestDto {
    @NonNull private final Long id;
    @NonNull private final String firstName;
    @NonNull private final String lastName;
    @NonNull private final String email;
    @NonNull private final String password;
}