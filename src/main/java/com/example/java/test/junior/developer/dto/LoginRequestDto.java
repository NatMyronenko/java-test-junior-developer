package com.example.java.test.junior.developer.dto;

import io.smallrye.common.constraint.NotNull;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
  @NotNull
  @NotBlank
  private String email;

  @NotNull
  @NotBlank
  private String password;
}