package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public CategoryDto createCategory(@RequestBody @Valid CategoryDto category) {
    return categoryService.createCategory(category);
  }

  @GetMapping
  public List<CategoryDto> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @PutMapping("/{id}")
  public CategoryDto updateCategory(@PathVariable int id,
                                    @RequestBody @Valid CategoryDto category) {
    return categoryService.updateCategory(id, category);
  }

  @DeleteMapping("/{id}")
  public void deleteCategory(@PathVariable int id) {
    categoryService.deleteCategory(id);
  }
}