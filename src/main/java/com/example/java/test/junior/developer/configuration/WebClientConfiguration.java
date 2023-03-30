package com.example.java.test.junior.developer.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Setter
@Configuration
public class WebClientConfiguration {

  @Value("${KEYCLOAK_BASE_URL:https://example.com/auth/realms/app/protocol/openid-connect/token}")
  private String tokenUrl;

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder()
        .baseUrl(tokenUrl)
        .clientConnector(new ReactorClientHttpConnector());
  }
}
