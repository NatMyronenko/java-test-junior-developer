package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

  private final CategoryService categoryService;

  @Autowired
  public QuestionMapper(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  public Question toEntity(QuestionDto dto) {
    return Question.builder()
        .category(categoryService.getCategory(dto.getId()))
        .name(dto.getName())
        .build();
  }

  public QuestionDto toDto(Question question) {
    return QuestionDto.builder()
        .id(question.getId())
        .name(question.getName())
        .build();
  }
}

