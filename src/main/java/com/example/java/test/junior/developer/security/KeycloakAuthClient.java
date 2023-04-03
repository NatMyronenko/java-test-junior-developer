package com.example.java.test.junior.developer.security;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class KeycloakAuthClient {

  private final WebClient webClient;
  private final KeycloakConfiguration keycloakConfiguration;

  private static final Logger logger = LoggerFactory.getLogger(KeycloakAuthClient.class);

  public LoginResponseDto getAccessToken(String username, String password) {
    var requestBody = buildRequestBody(username, password);
    logger.info("Sending authentication request to Keycloak with username: {}", username);
    return webClient.post()
        .uri(keycloakConfiguration.getTokenUri())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(LoginResponseDto.class)
        .doOnSuccess(response -> logger.info("Received successful authentication response from Keycloak"))
        .doOnError(error -> logger.error("Failed to authenticate with Keycloak due to {}", error.getMessage()))
        .block();
  }

  private MultiValueMap<String, String> buildRequestBody(String username, String password) {
    var body = new LinkedMultiValueMap<String, String>();
    body.add("client_id", keycloakConfiguration.getClientId());
    body.add("client_secret", keycloakConfiguration.getClientSecret());
    body.add("grant_type", "password");
    body.add("username", username);
    body.add("password", password);
    return body;
  }
}
