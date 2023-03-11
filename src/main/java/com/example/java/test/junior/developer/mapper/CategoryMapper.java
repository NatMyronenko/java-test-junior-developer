package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .category_name(dto.getCategory_name())
                //.language_id(dto.getLanguage_id())
                .build();
    }

    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .category_name(category.getCategory_name())
                //.language_id(category.getLanguage_id())
                .build();
    }
}

