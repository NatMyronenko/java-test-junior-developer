package com.example.java.test.junior.developer;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class QuestionMapperJunitTest {

    @InjectMocks
    private QuestionMapper questionMapper;

    @Test
    public void toEntity_ValidDto_ReturnsEntity() {
        QuestionDto questionDto = new QuestionDto(1, "What is polymorphism?");

        Question result = questionMapper.toEntity(questionDto);

        assertEquals(questionDto.getName(), result.getName());
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


