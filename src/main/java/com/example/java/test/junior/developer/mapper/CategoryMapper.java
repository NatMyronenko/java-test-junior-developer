package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  private final LanguageService languageService;

  @Autowired
  public CategoryMapper(LanguageService languageService) {
    this.languageService = languageService;
  }

  public Category toEntity(CategoryDto dto) {
    return Category.builder()
        .language(languageService.getLanguage(dto.getId()))
        .name(dto.getName())
        .build();
  }

  public CategoryDto toDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .build();
  }
}

