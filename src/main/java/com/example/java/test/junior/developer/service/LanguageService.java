package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.mapper.LanguageMapper;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LanguageService {

  private final LanguageMapper languageMapper;
  private final LanguageRepository languageRepository;

  @Transactional
  public LanguageDto createLanguage(LanguageDto dto) {
    final var language = languageMapper.toEntity(dto);
    final var saved = languageRepository.save(language);
    return languageMapper.toDto(saved);
  }

  @Transactional(readOnly = true)
  public List<LanguageDto> getAllLanguages() {
    final var languages = languageRepository.findAll();
    return languages.stream()
        .map(languageMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public LanguageDto updateLanguage(Long id, LanguageDto dto) {
    final var language = languageMapper.toEntity(dto);
    language.setId(id);
    final var saved = languageRepository.save(language);
    return languageMapper.toDto(saved);
  }

  @Transactional
  public void deleteLanguage(Long id) {
    languageRepository.deleteById(id);
  }
}