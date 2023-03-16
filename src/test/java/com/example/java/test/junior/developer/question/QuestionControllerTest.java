package com.example.java.test.junior.developer.question;
import com.example.java.test.junior.developer.controller.QuestionController;
import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.service.QuestionService;
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

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = QuestionController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;

    @SneakyThrows
    @Test
    void testCreateQuestion() {
        final var inputStream =
                new ClassPathResource("controller/question-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = QuestionDto.builder()
                .name("What is polymorphism?")
                .build();
        final var response = QuestionDto.builder()
                .id(1)
                .name("What is polymorphism?")
                .build();

        when(questionService.createQuestion(dto)).thenReturn(response);

        mockMvc.perform(post("/api/v1/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("What is polymorphism?")));
    }



    @SneakyThrows
    @Test
    void testGetAllQuestions() {
        final var response = QuestionDto.builder()
                .id(1)
                .name("What is polymorphism?")
                .build();
        when(questionService.getAllQuestions()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/questions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", equalTo(1)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("What is polymorphism?")));
    }

    @SneakyThrows
    @Test
    void testUpdateQuestion() {
        final var inputStream =
                new ClassPathResource("controller/question-request.json").getInputStream();
        final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
        final var dto = QuestionDto.builder()
                .name("What is polymorphism?")
                .build();
        final var response = QuestionDto.builder()
                .id(1)
                .name("What is polymorphism?")
                .build();

        when(questionService.updateQuestion(1, dto)).thenReturn(response);

        mockMvc.perform(put("/api/v1/questions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("What is polymorphism?")));

    }

    @SneakyThrows
    @Test
    void testDeleteQuestion() {
        mockMvc.perform(delete("/api/v1/questions/1"))
                .andExpect(status().isOk());

        verify(questionService).deleteQuestion(1);
    }

}
