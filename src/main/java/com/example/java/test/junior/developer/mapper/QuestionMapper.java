package com.example.java.test.junior.developer.mapper;

import com.example.java.test.junior.developer.dto.CategoryDto;
import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.model.Category;
import com.example.java.test.junior.developer.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public Question toEntity(QuestionDto dto) {
        return Question.builder()
                .name(dto.getName())
                .build();
    }

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .name(question.getName())
                .build();
    }
}

