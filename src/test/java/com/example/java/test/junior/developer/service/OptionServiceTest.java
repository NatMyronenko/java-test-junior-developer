package com.example.java.test.junior.developer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.mapper.OptionMapper;
import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.repository.OptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class OptionServiceTest {

  @Mock
  private OptionMapper optionMapper;

  @Mock
  private OptionRepository optionRepository;

  @InjectMocks
  private OptionService optionService;

  @Test
  public void createOptionTest() {
    OptionDto optionDto = OptionDto.builder()
        .answer("test answer")
        .isCorrect(true)
        .questionId(1L)
        .build();
    Option option = new Option();
    when(optionMapper.toModel(optionDto)).thenReturn(option);
    when(optionRepository.save(option)).thenReturn(option);
    when(optionMapper.toDto(option)).thenReturn(optionDto);
    OptionDto createdOptionDto = optionService.createOption(optionDto);
    assertEquals(optionDto, createdOptionDto);
  }

  @Test
  public void getOptionTest() {
    Long id = 1L;
    Option option = new Option();
    OptionDto optionDto = OptionDto.builder()
        .id(id)
        .answer("test answer")
        .isCorrect(true)
        .questionId(1L)
        .build();
    when(optionRepository.findById(id)).thenReturn(Optional.of(option));
    when(optionMapper.toDto(option)).thenReturn(optionDto);
    OptionDto retrievedOptionDto = optionService.getOption(id);
    assertEquals(optionDto, retrievedOptionDto);
  }

  @Test
  public void getAllOptionsTest() {
    List<Option> options = new ArrayList<>();
    Option option = new Option();
    options.add(option);
    OptionDto optionDto = OptionDto.builder()
        .id(1L)
        .answer("test answer")
        .isCorrect(true)
        .questionId(1L)
        .build();
    when(optionRepository.findAll()).thenReturn(options);
    when(optionMapper.toDto(option)).thenReturn(optionDto);
    List<OptionDto> allOptions = optionService.getAllOptions();
    assertEquals(1, allOptions.size());
    assertEquals(optionDto, allOptions.get(0));
  }

  @Test
  public void updateOptionTest() {
    Long id = 1L;
    OptionDto optionDto = OptionDto.builder()
        .answer("updated answer")
        .isCorrect(true)
        .questionId(2L)
        .build();
    Option option = new Option();
    option.setId(id);
    when(optionMapper.toModel(optionDto)).thenReturn(option);
    when(optionRepository.save(option)).thenReturn(option);
    when(optionMapper.toDto(option)).thenReturn(optionDto);
    OptionDto updatedOptionDto = optionService.updateOption(optionDto, id);
    assertEquals(optionDto, updatedOptionDto);
    assertEquals(id, option.getId());
  }

  @Test
  public void deleteOptionTest() {
    Long id = 1L;
    optionService.deleteOption(id);
    verify(optionRepository).deleteById(id);
  }
}


