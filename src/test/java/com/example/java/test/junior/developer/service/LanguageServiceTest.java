package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.mapper.LanguageMapper;
import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {

  @Mock
  private LanguageMapper languageMapper;

  @Mock
  private LanguageRepository languageRepository;

  @InjectMocks
  private LanguageService languageService;

  @Test
  public void createLanguageTest() {
    // given
    LanguageDto languageDto = new LanguageDto();
    Language language = new Language();
    when(languageMapper.toEntity(languageDto)).thenReturn(language);
    when(languageRepository.save(language)).thenReturn(language);
    when(languageMapper.toDto(language)).thenReturn(languageDto);

    // when
    LanguageDto createdLanguageDto = languageService.createLanguage(languageDto);

    // then
    assertEquals(languageDto, createdLanguageDto);
  }

  @Test
  public void getAllLanguagesTest() {
    // given
    List<Language> languages = new ArrayList<>();
    Language language = new Language();
    languages.add(language);
    when(languageRepository.findAll()).thenReturn(languages);

    LanguageDto languageDto = new LanguageDto();
    when(languageMapper.toDto(language)).thenReturn(languageDto);

    // when
    List<LanguageDto> allLanguages = languageService.getAllLanguages();

    // then
    assertEquals(1, allLanguages.size());
    assertEquals(languageDto, allLanguages.get(0));
  }

  @Test
  public void updateLanguageTest() {
    // given
    Long id = 1L;
    LanguageDto languageDto = new LanguageDto();
    Language language = new Language();
    language.setId(id);
    when(languageMapper.toEntity(languageDto)).thenReturn(language);
    when(languageRepository.save(language)).thenReturn(language);
    when(languageMapper.toDto(language)).thenReturn(languageDto);

    // when
    LanguageDto updatedLanguageDto = languageService.updateLanguage(id, languageDto);

    // then
    assertEquals(languageDto, updatedLanguageDto);
  }

  @Test
  public void deleteLanguageTest() {
    // given
    Long id = 1L;

    // when
    languageService.deleteLanguage(id);

    // then
    verify(languageRepository).deleteById(id);
  }
}
