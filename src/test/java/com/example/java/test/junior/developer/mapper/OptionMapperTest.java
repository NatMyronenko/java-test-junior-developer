package com.example.java.test.junior.developer.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.model.Question;
import com.example.java.test.junior.developer.repository.QuestionRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionMapperTest {

  private OptionMapper optionMapper;
  private QuestionRepository questionRepository;

  @BeforeEach
  public void setUp() {
    questionRepository = mock(QuestionRepository.class);
    optionMapper = new OptionMapper(questionRepository);
  }

  @Test
  public void toModel_ValidDto_ReturnsModel() {
    // Arrange
    OptionDto optionDto = new OptionDto(1L, "Option A", true, 1L);
    Question question = Question.builder().id(1L).name("What is polymorphism?").build();
    when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

    // Act
    Option option = optionMapper.toModel(optionDto);

    // Assert
    assertThat(option.getId()).isNull();
    assertThat(option.getAnswer()).isEqualTo(optionDto.getAnswer());
    assertThat(option.getIsCorrect()).isEqualTo(optionDto.getIsCorrect());
    assertThat(option.getQuestion()).isEqualTo(question);
  }

  @Test
  public void toDto_ValidModel_ReturnsDto() {
    // Arrange
    Question question = Question.builder().id(1L).name("What is polymorphism?").build();
    Option option = Option.builder().id(1L).answer("Option A").isCorrect(true).question(question)
        .build();

    // Act
    OptionDto optionDto = optionMapper.toDto(option);

    // Assert
    assertEquals(option.getId(), optionDto.getId());
    assertEquals(option.getAnswer(), optionDto.getAnswer());
    assertEquals(option.getIsCorrect(), optionDto.getIsCorrect());
  }

}
