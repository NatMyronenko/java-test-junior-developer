package com.example.java.test.junior.developer.repository;

import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestionId(Long questionId);
}
