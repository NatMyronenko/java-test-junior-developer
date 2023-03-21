package com.example.java.test.junior.developer.dto;

import com.example.java.test.junior.developer.model.Category;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class QuestionDto {

  Long id;

  @NotBlank String name;
  @NotBlank Category category;

}


