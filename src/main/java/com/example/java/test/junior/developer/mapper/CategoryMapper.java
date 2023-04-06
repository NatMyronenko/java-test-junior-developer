package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.service.LanguageService;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  LanguageService languageService;

  public Category toEntity(CategoryDto dto) {
    return Category.builder()
        .name(dto.getName())
        .language(languageService.getLanguage(dto.getId()))
        .build();
  }

  public CategoryDto toDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }
}

