package com.example.java.test.junior.developer.category;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.mapper.CategoryMapper;
import com.example.java.test.junior.developer.model.Category;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    private final CategoryMapper categoryMapper = new CategoryMapper();

    @Test
    void shouldMapToEntity() {
        // given
        CategoryDto dto = CategoryDto.builder()
                .id(1)
                .name("Сore")
                .build();

        // when
        Category category = categoryMapper.toEntity(dto);

        // then
        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(dto.getId());
        assertThat(category.getName()).isEqualTo(dto.getName());
    }

    @Test
    void shouldMapToDto() {
        // given
        Category category = Category.builder()
                .id(1)
                .name("Сore")
                .build();

        // when
        CategoryDto dto = categoryMapper.toDto(category);

        // then
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(category.getId());
        assertThat(dto.getName()).isEqualTo(category.getName());
    }
}
