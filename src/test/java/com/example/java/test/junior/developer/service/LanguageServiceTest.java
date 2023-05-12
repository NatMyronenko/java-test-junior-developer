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
    LanguageDto languageDto = new LanguageDto();
    Language language = new Language();
    when(languageMapper.toEntity(languageDto)).thenReturn(language);
    when(languageRepository.save(language)).thenReturn(language);
    when(languageMapper.toDto(language)).thenReturn(languageDto);
    LanguageDto createdLanguageDto = languageService.createLanguage(languageDto);
    assertEquals(languageDto, createdLanguageDto);
  }

  @Test
  public void getAllLanguagesTest() {
    List<Language> languages = new ArrayList<>();
    Language language = new Language();
    languages.add(language);
    when(languageRepository.findAll()).thenReturn(languages);

    LanguageDto languageDto = new LanguageDto();
    when(languageMapper.toDto(language)).thenReturn(languageDto);
    List<LanguageDto> allLanguages = languageService.getAllLanguages();
    assertEquals(1, allLanguages.size());
    assertEquals(languageDto, allLanguages.get(0));
  }

  @Test
  public void updateLanguageTest() {
    Long id = 1L;
    LanguageDto languageDto = new LanguageDto();
    Language language = new Language();
    language.setId(id);
    when(languageMapper.toEntity(languageDto)).thenReturn(language);
    when(languageRepository.save(language)).thenReturn(language);
    when(languageMapper.toDto(language)).thenReturn(languageDto);
    LanguageDto updatedLanguageDto = languageService.updateLanguage(id, languageDto);
    assertEquals(languageDto, updatedLanguageDto);
  }

  @Test
  public void deleteLanguageTest() {
    Long id = 1L;
    languageService.deleteLanguage(id);
    verify(languageRepository).deleteById(id);
  }
}
