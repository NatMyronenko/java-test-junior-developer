package com.example.java.test.junior.developer.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.model.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanguageMapperTest {

  private LanguageMapper languageMapper;

  @BeforeEach
  public void setUp() {
    languageMapper = new LanguageMapper();
  }

  @Test
  public void toEntity_ValidDto_ReturnsEntity() {
    LanguageDto languageDto = new LanguageDto(1L, "English");

    Language result = languageMapper.toEntity(languageDto);

    assertThat(result.getId()).isNull();
    assertThat(result.getName()).isEqualTo(languageDto.getName());
  }

  @Test
  public void toDto_ValidEntity_ReturnsDto() {
    Language language = Language.builder()
        .id(1L)
        .name("French")
        .build();

    LanguageDto result = languageMapper.toDto(language);

    assertEquals(language.getId(), result.getId());
    assertEquals(language.getName(), result.getName());
  }
}
