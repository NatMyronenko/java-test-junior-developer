package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void createQuestionTest() {
        // given
        QuestionDto questionDto = new QuestionDto();
        Question question = new Question();
        when(questionMapper.toEntity(questionDto)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(question);
        when(questionMapper.toDto(question)).thenReturn(questionDto);

        // when
        QuestionDto createdQuestionDto = questionService.createQuestion(questionDto);

        // then
        assertEquals(questionDto, createdQuestionDto);
    }

    @Test
    public void getAllQuestionsTest() {
        // given
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        questions.add(question);
        when(questionRepository.findAll()).thenReturn(questions);

        QuestionDto questionDto = new QuestionDto();
        when(questionMapper.toDto(question)).thenReturn(questionDto);

        // when
        List<QuestionDto> allQuestions = questionService.getAllQuestions();

        // then
        assertEquals(1, allQuestions.size());
        assertEquals(questionDto, allQuestions.get(0));
    }

    @Test
    public void updateQuestionTest() {
        // given
        int id = 1;
        QuestionDto questionDto = new QuestionDto();
        Question question = new Question();
        question.setId(id);
        when(questionMapper.toEntity(questionDto)).thenReturn(question);
        when(questionRepository.save(question)).thenReturn(question);
        when(questionMapper.toDto(question)).thenReturn(questionDto);

        // when
        QuestionDto updatedQuestionDto = questionService.updateQuestion(id, questionDto);

        // then
        assertEquals(questionDto, updatedQuestionDto);
    }

    @Test
    public void deleteQuestionTest() {
        // given
        int id = 1;

        // when
        questionService.deleteQuestion(id);

        // then
        verify(questionRepository).deleteById(id);
    }
}
