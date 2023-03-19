package com.example.java.test.junior.developer.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionMapperTest {

    private QuestionMapper questionMapper;

    @BeforeEach
    public void setUp() {
        questionMapper = new QuestionMapper();
    }

    @Test
    public void toEntity_ValidDto_ReturnsEntity() {
        QuestionDto questionDto = new QuestionDto(1L, "What is polymorphism?");

        Question result = questionMapper.toEntity(questionDto);
        assertThat(result.getId()).isNull();
        assertThat(result.getName()).isEqualTo(questionDto.getName());

    }

    @Test
    public void toDto_ValidEntity_ReturnsDto() {
        Question question = Question.builder()
                .id(1L)
                .name("What is polymorphism?")
                .build();

        QuestionDto result = questionMapper.toDto(question);

        assertEquals(question.getId(), result.getId());
        assertEquals(question.getName(), result.getName());
    }
}

