package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class OptionDto {

  @NotNull Long id;
  @NotNull String answer;
  @NotNull Boolean isCorrect;
}