package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/save")
    public Language createLanguage(@RequestBody Language language) {
        return languageService.createLanguage(language);
    }

    @GetMapping("/get")
    public List<Language> getLanguages() {
        return languageService.getAllLanguages();
    }

    @PutMapping("/update/{id}")
    public Language updateLanguage(Long id, Language language) {
        return languageService.updateLanguage(id, language);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        if (languageService.getAllLanguages().get(id.intValue()) != null) {
            deleteLanguage(id);
        }
    }
}
