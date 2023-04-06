package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

  CategoryService categoryService;

  public Question toEntity(QuestionDto dto) {
    return Question.builder()
        .name(dto.getName())
        .category(categoryService.getCategory(dto.getId()))
        .build();
  }

  public QuestionDto toDto(Question question) {
    return QuestionDto.builder()
        .id(question.getId())
        .name(question.getName())
        .build();
  }
}

