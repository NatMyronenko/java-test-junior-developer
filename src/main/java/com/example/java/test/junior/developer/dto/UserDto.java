package com.example.java.test.junior.developer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserDto {

    int id;

    @NotBlank String name;
    @NotBlank String surname;
    @NotBlank String email;

}
