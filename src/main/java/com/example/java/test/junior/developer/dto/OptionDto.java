package com.example.java.test.junior.developer.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Builder
@RequiredArgsConstructor
public class OptionDto {

  Long id;
  String answer;
  String questionId;
  Boolean isCorrect;
}
