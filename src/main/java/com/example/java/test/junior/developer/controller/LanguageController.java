package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.service.LanguageService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping
    public LanguageDto createLanguage(@RequestBody @Valid LanguageDto language) {
        return languageService.createLanguage(language);
    }

    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getAllLanguages();
    }

    @PutMapping("/{id}")
    public LanguageDto updateLanguage(@PathVariable Long id,
                                      @RequestBody @Valid LanguageDto language) {
        return languageService.updateLanguage(id, language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
    }

}
