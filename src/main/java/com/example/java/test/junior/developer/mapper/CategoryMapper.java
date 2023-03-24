package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {

  //  public Category toEntity(CategoryDto dto) {
//    return Category.builder()
//        .name(dto.getName())
//        .language(Language.builder().build())
//        .build();

  private final LanguageRepository languageRepository;

  public Category toEntity(CategoryDto dto) {
    Language language = languageRepository.findById(dto.getIdLanguage())
        .orElseThrow(() -> new IllegalArgumentException("Language not found"));
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

