package com.example.java.test.junior.developer.category;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.java.test.junior.developer.controller.CategoryController;
import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.service.CategoryService;
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

@WebMvcTest(controllers = CategoryController.class,
    excludeAutoConfiguration = SecurityAutoConfiguration.class)
class CategoryControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private CategoryService categoryService;

  @SneakyThrows
  @Test
  void testCreateCategory() {
    final var inputStream =
        new ClassPathResource("controller/category-request.json").getInputStream();
    final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    final var dto = CategoryDto.builder()
        .name("SpringBoot")
        .build();
    final var response = CategoryDto.builder()
        .id(1)
        .name("SpringBoot")
        .build();

    when(categoryService.createCategory(dto)).thenReturn(response);

    mockMvc.perform(post("/api/v1/categories")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.name", equalTo("SpringBoot")));
  }

  @SneakyThrows
  @Test
  void testGetAllCategories() {
    final var response = CategoryDto.builder()
        .id(1)
        .name("SpringBoot")
        .build();
    when(categoryService.getAllCategories()).thenReturn(List.of(response));

    mockMvc.perform(get("/api/v1/categories"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()", equalTo(1)))
        .andExpect(jsonPath("$[0].id", equalTo(1)))
        .andExpect(jsonPath("$[0].name", equalTo("SpringBoot")));
  }

  @SneakyThrows
  @Test
  void testUpdateCategory() {
    final var inputStream =
        new ClassPathResource("controller/category-request.json").getInputStream();
    final var requestBody = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
    final var dto = CategoryDto.builder()
        .name("SpringBoot")
        .build();
    final var response = CategoryDto.builder()
        .id(1)
        .name("SpringBoot")
        .build();

    when(categoryService.updateCategory(1, dto)).thenReturn(response);

    mockMvc.perform(put("/api/v1/categories/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.name", equalTo("SpringBoot")));

  }

  @SneakyThrows
  @Test
  void testDeleteCategory() {
    mockMvc.perform(delete("/api/v1/categories/1"))
        .andExpect(status().isOk());

    verify(categoryService).deleteCategory(1);
  }

}