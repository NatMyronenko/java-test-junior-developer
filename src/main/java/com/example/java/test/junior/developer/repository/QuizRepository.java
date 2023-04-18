package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
