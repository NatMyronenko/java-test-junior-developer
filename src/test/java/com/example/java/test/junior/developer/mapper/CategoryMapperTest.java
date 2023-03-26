package com.example.java.test.junior.developer.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryMapperTest {

  private CategoryMapper categoryMapper;

  @BeforeEach
  void setUp() {
    categoryMapper = new CategoryMapper();
  }

  @Test
  void toEntity_ValidDto_ReturnsEntity() {
    CategoryDto categoryDto = new CategoryDto(1L, "SpringBoot");

    Category result = categoryMapper.toEntity(categoryDto);

    assertThat(result.getId()).isNull();
    assertThat(result.getName()).isEqualTo(categoryDto.getName());
  }

  @Test
  void toDto_ValidEntity_ReturnsDto() {
    Category category = Category.builder()
        .id(1L)
        .name("SpringBoot")
        .build();

    CategoryDto result = categoryMapper.toDto(category);

    assertEquals(category.getId(), result.getId());
    assertEquals(category.getName(), result.getName());
  }
}

