package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class OptionDto {

  Long id;
  @NotBlank String answer;
  @NotNull Boolean isCorrect;
  Long questionId;
}