package com.example.java.test.junior.developer.language;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.controller.LanguageController;
import com.example.java.test.junior.developer.dto.LanguageDto;
import com.example.java.test.junior.developer.service.LanguageService;
import java.nio.charset.Charset;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;


@WebMvcTest(
    controllers = LanguageController.class,
    excludeAutoConfiguration = SecurityAutoConfiguration.class
)
class LanguageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LanguageService languageService;

    @SneakyThrows
    @Test
    void testCreateLanguage() {
        final var inputStream =
            new ClassPathResource("controller/language-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = LanguageDto.builder()
            .name("Java")
            .build();
        final var response = LanguageDto.builder()
            .id(1L)
            .name("Java")
            .build();

        when(languageService.createLanguage(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/languages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.name",  equalTo("Java")));
    }


    @SneakyThrows
    @Test
    void testGetAllLanguages() {
        final var response = LanguageDto.builder()
            .id(1L)
            .name("Java")
            .build();
        when(languageService.getAllLanguages()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/languages"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()", equalTo(1)))
            .andExpect(jsonPath("$[0].id", equalTo(1)))
            .andExpect(jsonPath("$[0].name",  equalTo("Java")));
    }

    @SneakyThrows
    @Test
    void testUpdateLanguage() {
        final var inputStream =
            new ClassPathResource("controller/language-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = LanguageDto.builder()
            .name("Java")
            .build();
        final var response = LanguageDto.builder()
            .id(1L)
            .name("Java")
            .build();

        when(languageService.updateLanguage(1L, dto)).thenReturn(response);

        mockMvc.perform(put("/api/v1/languages/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(1)))
            .andExpect(jsonPath("$.name",  equalTo("Java")));
    }

    @SneakyThrows
    @Test
    void testDeleteLanguage() {
        mockMvc.perform(delete("/api/v1/languages/1"))
            .andExpect(status().isOk());

        verify(languageService).deleteLanguage(1L);
    }

}
