package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.mapper.CategoryMapper;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @Mock
  private CategoryMapper categoryMapper;

  @Mock
  private CategoryRepository categoryRepository;

  @InjectMocks
  private CategoryService categoryService;

  @Test
  public void createCategoryTest() {
    // given
    CategoryDto categoryDto = new CategoryDto();
    Category category = new Category();
    when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
    when(categoryRepository.save(category)).thenReturn(category);
    when(categoryMapper.toDto(category)).thenReturn(categoryDto);

    // when
    CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);

    // then
    assertEquals(categoryDto, createdCategoryDto);
  }

  @Test
  public void getAllCategoriesTest() {
    // given
    List<Category> categories = new ArrayList<>();
    Category category = new Category();
    categories.add(category);
    when(categoryRepository.findAll()).thenReturn(categories);

    CategoryDto categoryDto = new CategoryDto();
    when(categoryMapper.toDto(category)).thenReturn(categoryDto);

    // when
    List<CategoryDto> allCategories = categoryService.getAllCategories();
    // then
    assertEquals(1, allCategories.size());
    assertEquals(categoryDto, allCategories.get(0));
  }

  @Test
  public void updateCategoryTest() {
    // given
    Long id = 1L;
    CategoryDto categoryDto = new CategoryDto();
    Category category = new Category();
    category.setId(id);
    when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
    when(categoryRepository.save(category)).thenReturn(category);
    when(categoryMapper.toDto(category)).thenReturn(categoryDto);

    // when
    CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);

    // then
    assertEquals(categoryDto, createdCategoryDto);
  }

  @Test
  public void deleteCategoryTest() {
    // given
    Long id = 1L;

    // when
    categoryService.deleteCategory(id);

    // then
    verify(categoryRepository).deleteById(id);
  }
}


