package com.example.java.test.junior.developer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Setter
@Configuration
public class WebClientConfiguration {

  @Value("${keycloak.authServerUrl}")
  private String tokenUrl;

  @Bean
  public WebClient keycloakWebClient() {
    return WebClient.builder()
        .baseUrl(tokenUrl)
        .build();
  }
}
