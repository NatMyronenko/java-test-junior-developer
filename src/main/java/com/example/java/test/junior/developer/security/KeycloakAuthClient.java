package com.example.java.test.junior.developer.security;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class KeycloakAuthClient {

  private final WebClient webClient;
  private final KeycloakConfiguration keycloakConfiguration;

  public String getAccessToken(String username, String password) {
    var requestBody = buildRequestBody(username, password);
    return webClient.post()
        .uri(keycloakConfiguration.getTokenUri())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(LoginResponseDto.class)
        .blockOptional()
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"))
        .getAccessToken();
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
