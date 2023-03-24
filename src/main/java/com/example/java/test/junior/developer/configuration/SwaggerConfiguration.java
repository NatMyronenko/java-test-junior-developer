package com.example.java.test.junior.developer.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
        .info(new Info().title("Java Test Junior").description("Quiz web app").version("0.0.1"));
  }
}