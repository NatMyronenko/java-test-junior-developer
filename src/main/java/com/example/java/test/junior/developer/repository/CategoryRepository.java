package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}