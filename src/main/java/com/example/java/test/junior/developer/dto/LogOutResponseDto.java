package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class LogOutResponseDto {


  @NotBlank String accessToken;

  @NotBlank String refreshToken;

}




