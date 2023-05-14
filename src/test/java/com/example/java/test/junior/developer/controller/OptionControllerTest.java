package com.example.java.test.junior.developer.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.service.OptionService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StreamUtils;

@WebMvcTest(
    controllers = OptionController.class,
    excludeAutoConfiguration = SecurityAutoConfiguration.class)
@WithMockUser
class OptionControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private OptionService optionService;

  @SneakyThrows
  @Test
  void testCreateOption() {
    final var inputStream =
        new ClassPathResource("controller/option-request.json").getInputStream();
    final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    final var dto = OptionDto.builder()
        .answer("Option 1")
        .isCorrect(true)
        .questionId(1L)
        .build();
    final var response = OptionDto.builder()
        .id(1L)
        .answer("Option 1")
        .isCorrect(true)
        .questionId(1L)
        .build();

    when(optionService.createOption(dto)).thenReturn(response);

    mockMvc.perform(post("/api/v1/options")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.answer", equalTo("Option 1")))
        .andExpect(jsonPath("$.isCorrect", equalTo(true)))
        .andExpect(jsonPath("$.questionId", equalTo(1)));
  }

  @SneakyThrows
  @Test
  void testGetOption() {
    final var response = OptionDto.builder()
        .id(1L)
        .answer("Option 1")
        .isCorrect(true)
        .questionId(1L)
        .build();
    when(optionService.getOption(1L)).thenReturn(response);

    mockMvc.perform(get("/api/v1/options/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.answer", equalTo("Option 1")))
        .andExpect(jsonPath("$.isCorrect", equalTo(true)))
        .andExpect(jsonPath("$.questionId", equalTo(1)));
  }

  @SneakyThrows
  @Test
  void testGetAllOptions() {
    final var response = OptionDto.builder()
        .id(1L)
        .answer("Option 1")
        .isCorrect(true)
        .questionId(1L)
        .build();
    when(optionService.getAllOptions()).thenReturn(List.of(response));

    mockMvc.perform(get("/api/v1/options"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$[0].id", equalTo(1)))
        .andExpect(jsonPath("$[0].answer", equalTo("Option 1")))
        .andExpect(jsonPath("$[0].isCorrect", equalTo(true)))
        .andExpect(jsonPath("$[0].questionId", equalTo(1)));
  }

  @SneakyThrows
  @Test
  void testUpdateOption() {
    final var inputStream =
        new ClassPathResource("controller/option-request2.json").getInputStream();
    final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    final var dto = OptionDto.builder()
        .answer("Option 2")
        .isCorrect(true)
        .questionId(1L)
        .build();
    final var response = OptionDto.builder()
        .id(1L)
        .answer("Option 2")
        .isCorrect(true)
        .questionId(1L)
        .build();
    when(optionService.updateOption(dto, 1L)).thenReturn(response);

    mockMvc.perform(put("/api/v1/options/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.answer", equalTo("Option 2")))
        .andExpect(jsonPath("$.isCorrect", equalTo(true)))
        .andExpect(jsonPath("$.questionId", equalTo(1)));
  }

  @Test
  void testDeleteOption() throws Exception {
    mockMvc.perform(delete("/api/v1/options/1"))
        .andExpect(status().isOk());

    verify(optionService).deleteOption(1L);
  }
}

