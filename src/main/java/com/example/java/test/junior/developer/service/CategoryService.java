package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.mapper.CategoryMapper;
import com.example.java.test.junior.developer.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Transactional
    public CategoryDto createCategory(CategoryDto dto) {
        final var category = categoryMapper.toEntity(dto);
        final var saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> getAllCategories() {
        final var categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        final var category = categoryMapper.toEntity(dto);
        category.setId(id);
        final var saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }

    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}

