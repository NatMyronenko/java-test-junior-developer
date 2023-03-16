package com.example.java.test.junior.developer.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public final class UserDto {
    @NonNull private final Long id;
    @NonNull private final String firstName;
    @NonNull private final String lastName;
    @NonNull private final String email;
}
