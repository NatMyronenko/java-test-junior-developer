package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.UserQuizAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuizAnswersRepository extends JpaRepository<UserQuizAnswers, Long> {
}
