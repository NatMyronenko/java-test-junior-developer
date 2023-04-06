package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {

  private final QuestionService questionService;

  @Autowired
  public OptionMapper(QuestionService questionService) {
    this.questionService = questionService;
  }

  public Option toModel(OptionDto optionDto) {
    return Option.builder()
        .answer(optionDto.getAnswer())
        .question(questionService.getQuestion(optionDto.getId()))
        .isCorrect(optionDto.getIsCorrect())
        .build();
  }

  public OptionDto toDto(Option option) {
    return OptionDto.builder()
        .id(option.getId())
        .answer(option.getAnswer())
        .isCorrect(option.getIsCorrect())
        .build();
  }
}