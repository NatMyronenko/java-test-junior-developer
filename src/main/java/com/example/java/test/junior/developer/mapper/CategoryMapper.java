package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.model.Language;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

  public Category toEntity(CategoryDto dto) {
    Language language = Language.builder()
        .id(dto.getIdLanguage())
        .build();

    return Category.builder()
        .name(dto.getName())
        .language(language)
        .build();
  }

  public CategoryDto toDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .name(category.getName())
        .idLanguage(category.getLanguage().getId())
        .build();
  }
}

