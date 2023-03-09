package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.exception.LanguageNotFoundException;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Language updateLanguage(Long id, Language language) {
        Optional<Language> existingLanguage = languageRepository.findById(id);
        if (existingLanguage.isPresent()) {
            Language updatedLanguage = existingLanguage.get();
            updatedLanguage.setName(language.getName());
            return languageRepository.save(updatedLanguage);
        } else {
            throw new LanguageNotFoundException("Language not found with id " + id);
        }
    }

    public void deleteLanguage(Long id) {
        Optional<Language> existingLanguage = languageRepository.findById(id);
        if (existingLanguage.isPresent()) {
            languageRepository.deleteById(id);
        } else {
            throw new LanguageNotFoundException("Language not found with id " + id);
        }
    }

}

