package com.example.java.test.junior.developer.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryDto {

  Long id;

  @NotBlank String name;
  Long idLanguage;


}

