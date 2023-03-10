package com.example.java.test.junior.developer;


import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.service.LanguageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class LanguageTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LanguageService languageService;



    @Test
    void testCreateLanguage() throws Exception {
        Language language = new Language(1L, "Java");

        when(languageService.createLanguage(language)).thenReturn(languageService.getAllLanguages().get(0));

        mockMvc.perform(post("/api/v1/languages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(language)))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Java"));

        verify(languageService, times(1)).createLanguage(any(Language.class));
        verifyNoMoreInteractions(languageService);

    }


    @Test
    void testGetAllLanguages() throws Exception {
        when(languageService.getAllLanguages()).thenReturn(languageService.getAllLanguages());

        mockMvc.perform(get("/api/v1/languages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].name").value("Python"));

        verify(languageService, times(1)).getAllLanguages();
        verifyNoMoreInteractions(languageService);
    }

    @Test
    void testUpdateLanguage() throws Exception {
        Language java = languageService.getAllLanguages().get(0);
        Language updatedJava = new Language();
        updatedJava.setName("Updated Java");

        when(languageService.updateLanguage(java.getId(), updatedJava)).thenReturn(updatedJava);

        mockMvc.perform(put("/api/v1/languages/{id}", java.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedJava)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(java.getId().intValue()))
                .andExpect(jsonPath("$.name").value("Updated Java"));

        verify(languageService, times(1)).updateLanguage(eq(java.getId()), any(Language.class));
        verifyNoMoreInteractions(languageService);
    }

    @Test
    void testDeleteLanguage() throws Exception {
        Language java = languageService.getAllLanguages().get(0);

        mockMvc.perform(delete("/api/v1/languages/{id}", java.getId()))
                .andExpect(status().isOk());

        verify(languageService, times(1)).deleteLanguage(java.getId());
        verifyNoMoreInteractions(languageService);
    }

}
