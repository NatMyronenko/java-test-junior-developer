package com.example.java.test.junior.developer.security;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
public class KeycloakAuthClient {

  private final WebClient webClient;
  private final KeycloakConfiguration keycloakConfiguration;

  public KeycloakAuthClient(WebClient.Builder webClientBuilder, KeycloakConfiguration keycloakConfiguration) {
    this.webClient = webClientBuilder.build();
    this.keycloakConfiguration = keycloakConfiguration;
  }

  public Mono<String> getAccessToken(String username, String password) {
    var requestBody = buildRequestBody(username, password);
    return webClient.post()
        .uri(keycloakConfiguration.getTokenUrl())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(Map.class)
        .map(map -> (String) map.get("access_token"))
        .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials")));
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

