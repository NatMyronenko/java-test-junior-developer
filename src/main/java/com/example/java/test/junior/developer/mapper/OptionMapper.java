package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.model.Option;

public class OptionMapper {

  public Option toModel(OptionDto optionDto) {
    return Option.builder()
        .answer(optionDto.getAnswer())
        .questionId(optionDto.getQuestionId())
        .isCorrect(optionDto.getIsCorrect())
        .build();
  }

  public OptionDto toDto(Option option) {
    return OptionDto.builder()
        .answer(option.getAnswer())
        .questionId(option.getQuestionId())
        .isCorrect(option.getIsCorrect())
        .build();
  }
}
