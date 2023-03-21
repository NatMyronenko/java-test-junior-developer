package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.mapper.CategoryMapper;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.model.Language;
import com.example.java.test.junior.developer.repository.CategoryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CategoryServiceTest {

  private final CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
  private final CategoryMapper categoryMapper = new CategoryMapper();
  private final CategoryService categoryService = new CategoryService(categoryRepository,
      categoryMapper);

  @Test
  public void createCategory_ValidDto_ReturnsDtoWithId() {
    CategoryDto categoryDto = new CategoryDto(1L, "SpringBoot", Language.builder().build());
    Category category = Category.builder()
        .id(1L)
        .name(categoryDto.getName())
        .language(categoryDto.getLanguage())
        .build();

    when(categoryRepository.save(any(Category.class))).thenReturn(category);

    CategoryDto result = categoryService.createCategory(categoryDto);

    assertEquals(category.getId(), result.getId());
    assertEquals(category.getName(), result.getName());
    assertEquals(category.getLanguage(), result.getLanguage());
  }

  @Test
  public void getAllCategories_NoUsers_ReturnsEmptyList() {
    when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
    List<CategoryDto> result = categoryService.getAllCategories();
    assertEquals(Collections.emptyList(), result);
  }

  @Test
  public void updateCategory_ValidIdAndDto_ReturnsDtoWithUpdatedFields() {
    Long id = 1L;
    CategoryDto categoryDto = new CategoryDto(id, "SpringBoot", Language.builder().build());
    Category existingCategory = Category.builder()
        .id(id)
        .name("SpringBoot")
        .language(Language.builder().build())
        .build();
    Category updatedCategory = Category.builder()
        .id(id)
        .name(categoryDto.getName())
        .language(Language.builder().build())
        .build();

    when(categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
    when(categoryRepository.save(any(Category.class))).thenReturn(updatedCategory);

    CategoryDto result = categoryService.updateCategory(id, categoryDto);

    assertEquals(id, result.getId());
    assertEquals(categoryDto.getName(), result.getName());
    assertEquals(categoryDto.getLanguage(), result.getLanguage());
  }

  @Test
  public void deleteCategory_ValidId_DeletesUser() {
    Long id = 1L;
    categoryService.deleteCategory(id);
    Mockito.verify(categoryRepository).deleteById(id);
  }
}


