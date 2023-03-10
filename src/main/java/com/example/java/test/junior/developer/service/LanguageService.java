package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LanguageService{

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }


    public Language updateLanguage(Long id, Language language) {
        Language existingLanguage = languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id " + id));
        existingLanguage.setName(language.getName());

        return languageRepository.save(existingLanguage);
    }

    public void deleteLanguage(Long id) {
        languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with id " + id));
        languageRepository.deleteById(id);
    }

}

