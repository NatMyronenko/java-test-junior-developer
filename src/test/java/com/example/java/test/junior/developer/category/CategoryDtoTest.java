package com.example.java.test.junior.developer.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.java.test.junior.developer.dto.CategoryDto;
import org.junit.jupiter.api.Test;

public class CategoryDtoTest {

    @Test
    public void testCreateCategoryDto() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1)
                .name("SpringBoot")
                .build();
        assertEquals(1, categoryDto.getId());
        assertEquals("SpringBoot", categoryDto.getName());
    }
}


