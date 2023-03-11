package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

  public Language toEntity(LanguageDto dto) {
    return Language.builder()
        .name(dto.getName())
        .build();
  }

  public LanguageDto toDto(Language language) {
    return LanguageDto.builder()
        .id(language.getId())
        .name(language.getName())
        .build();
  }

}

