package com.example.java.test.junior.developer.service;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.mapper.OptionMapper;
import com.example.java.test.junior.developer.model.Option;
import com.example.java.test.junior.developer.repository.OptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptionService {

  private final OptionMapper optionMapper;
  private final OptionRepository optionRepository;

  @Transactional
  public OptionDto createOption(OptionDto optionDto) {
    final Option option = optionMapper.toModel(optionDto);
    final Option savedOption = optionRepository.save(option);
    return optionMapper.toDto(savedOption);
  }

  @Transactional(readOnly = true)
  public OptionDto getOption(OptionDto optionDto, Long id) {
    final Option option = optionRepository.findById(id).orElse(null);
    return option != null ? optionMapper.toDto(option) : null;
  }

  @Transactional(readOnly = true)
  public List<OptionDto> getAllOptions() {
    return optionRepository.findAll().stream()
        .map(optionMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public OptionDto updateOption(OptionDto optionDto, Long id) {
    final Option option = optionMapper.toModel(optionDto);
    option.setId(id);
    final Option savedOption = optionRepository.save(option);
    return optionMapper.toDto(savedOption);
  }

  @Transactional
  public void deleteOption(Long id){
    optionRepository.deleteById(id);
  }
}
