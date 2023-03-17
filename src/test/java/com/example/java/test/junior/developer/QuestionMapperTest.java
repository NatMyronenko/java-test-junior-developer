package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class QuestionMapperTest {

    @Mock
    private QuestionMapper questionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionMapper = new QuestionMapper();
    }

    @Test
    public void toEntity_ValidDto_ReturnsEntity() {
        QuestionDto questionDto = new QuestionDto(0, "What is polymorphism?");

        Question result = questionMapper.toEntity(questionDto);

        assertEquals(questionDto.getName(), result.getName());
        assertEquals(questionDto.getId(), result.getId());
    }

    @Test
    public void toDto_ValidEntity_ReturnsDto() {
        Question question = Question.builder()
                .id(1)
                .name("What is polymorphism?")
                .build();

        QuestionDto result = questionMapper.toDto(question);

        assertEquals(question.getId(), result.getId());
        assertEquals(question.getName(), result.getName());
    }
}

