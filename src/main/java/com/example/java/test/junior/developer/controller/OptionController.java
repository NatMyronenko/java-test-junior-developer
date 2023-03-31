package com.example.java.test.junior.developer.controller;

import com.example.java.test.junior.developer.dto.OptionDto;
import com.example.java.test.junior.developer.service.OptionService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
public class OptionController {

  private final OptionService optionService;

  @PostMapping
  public OptionDto createOption(@RequestBody @Valid OptionDto optionDto) {
    return optionService.createOption(optionDto);
  }

  @GetMapping("/{id}")
  public OptionDto getOption(@PathVariable @Valid Long id) {
    return optionService.getOption(id);
  }

  @GetMapping
  public List<OptionDto> getAllOptions() {
    return optionService.getAllOptions();
  }

  @PutMapping("/{id}")
  public OptionDto updateOption(@PathVariable @Valid Long id,
      @RequestBody @Valid OptionDto optionDto) {
    return optionService.updateOption(optionDto, id);
  }

  @DeleteMapping("/{id}")
  public void deleteOption(@PathVariable Long id) {
    optionService.deleteOption(id);
  }
}
