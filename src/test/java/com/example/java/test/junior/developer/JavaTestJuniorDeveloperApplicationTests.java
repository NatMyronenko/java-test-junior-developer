package com.example.java.test.junior.developer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.LanguageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class JavaTestJuniorDeveloperApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LanguageRepository languageRepository;

    @BeforeEach
    public void beforeEach() {
        languageRepository.deleteAll();
    }

    @Test
    void testCreateLanguage() throws Exception {
        Language language = new Language();
        language.setName("Java");

        mockMvc.perform(post("/api/v1/languages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(language)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Java"));
    }

    @Test
    void testGetAllLanguages() throws Exception {
        Language java = new Language("Java");
        Language python = new Language("Python");
        languageRepository.saveAll(Arrays.asList(java, python));

        mockMvc.perform(get("/api/v1/languages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].name").value("Python"));
    }

    @Test
    void testUpdateLanguage() throws Exception {
        Language java = new Language("Java");
        languageRepository.save(java);

        Language language = new Language();
        language.setName("Updated Java");

        mockMvc.perform(put("/api/v1/languages/{id}", java.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(language)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(java.getId().intValue()))
                .andExpect(jsonPath("$.name").value("Updated Java"));
    }

    @Test
    void testDeleteLanguage() throws Exception {
        Language java = new Language("Java");
        languageRepository.save(java);

        mockMvc.perform(delete("/api/v1/languages/{id}", java.getId()))
                .andExpect(status().isOk());

        assertFalse(languageRepository.findById(java.getId()).isPresent());
    }
}
