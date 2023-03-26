package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.QuestionDto;
import com.example.java.test.junior.developer.mapper.QuestionMapper;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

  @Mock
  private QuestionMapper questionMapper;

  @Mock
  private QuestionRepository questionRepository;

  @InjectMocks
  private QuestionService questionService;

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
    Long id = 1L;
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
    Long id = 1L;

    // when
    questionService.deleteQuestion(id);

    // then
    verify(questionRepository).deleteById(id);
  }
}
