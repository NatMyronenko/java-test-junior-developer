package com.example.java.test.junior.developer.category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.mapper.CategoryMapper;
import com.example.java.test.junior.developer.model.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryMapperJunitTest {

    @InjectMocks
    private CategoryMapper categoryMapper;

    @Test
    public void toEntity_ValidDto_ReturnsEntity() {
        CategoryDto categoryDto = new CategoryDto(1, "SpringBoot");

        Category result = categoryMapper.toEntity(categoryDto);

        assertEquals(categoryDto.getName(), result.getName());
    }

    @Test
    public void toDto_ValidEntity_ReturnsDto() {
        Category category = Category.builder()
                .id(1)
                .name("SpringBoot")
                .build();

        CategoryDto result = categoryMapper.toDto(category);

        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
    }
}

