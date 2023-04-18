package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.QuizDto;
import com.example.java.test.junior.developer.model.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {

    public static QuizDto toDto(Quiz quiz) {
        return QuizDto.builder()
                .quiz_id(quiz.getId())
                .question_id(quiz.getQuestion().getId())
                .option_id(quiz.getOption().getId())
                .build();
    }
}
