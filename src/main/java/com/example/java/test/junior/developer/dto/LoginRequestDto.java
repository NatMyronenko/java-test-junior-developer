package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@Builder
@RequiredArgsConstructor
public class LoginRequestDto {

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email is required")
  String email;

  @NotBlank(message = "Password is required")
  String password;
}