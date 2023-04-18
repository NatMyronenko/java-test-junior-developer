package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OptionMapper {

  private final QuestionRepository questionRepository;

  public Option toModel(OptionDto optionDto) {
    return Option.builder()
        .answer(optionDto.getAnswer())
        .question(questionRepository.findById(optionDto.getQuestionId()).orElse(null))
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