package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LoginRequestDto {

  @Email(message = "Invalid email format")
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;
}