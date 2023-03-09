package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.exception.LanguageNotFoundException;
import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {
    private final LanguageRepository languageRepository;

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return languageRepository.save(language);
    }

    @GetMapping
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    @PutMapping("/{id}")
    public Language updateLanguage(@PathVariable Long id, @RequestBody Language languageRequest) {
        return languageRepository.findById(id).map(language -> {
            language.setName(languageRequest.getName());
            return languageRepository.save(language);
        }).orElseThrow(() -> new LanguageNotFoundException("Language not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Long id) {
        return languageRepository.findById(id).map(language -> {
            languageRepository.delete(language);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new LanguageNotFoundException("Language not found with id " + id));
    }
}
