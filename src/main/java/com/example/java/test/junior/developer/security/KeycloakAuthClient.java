package com.example.java.test.junior.developer.security;

import com.example.java.test.junior.developer.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakAuthClient {

  private final WebClient webClient;
  private final KeycloakConfiguration keycloakConfiguration;

  public LoginResponseDto getAccessToken(String username, String password) {
    var requestBody = buildRequestBody(username, password);
    log.info("Sending authentication request to Keycloak with username: {}", username);
    return webClient.post()
        .uri(keycloakConfiguration.getTokenUri())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(LoginResponseDto.class)
        .doOnSuccess(
            response -> log.info("Received successful authentication response from Keycloak"))
        .doOnError(error -> log.error("Failed to authenticate with Keycloak due to {}",
            error.getMessage()))
        .block();
  }
  public ResponseEntity<Void> logout(String authorizationHeader, String refreshToken) {
    var requestBody = buildLogoutRequestBody(refreshToken);

    webClient.post()
        .uri(keycloakConfiguration.getLogoutUri())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
        .bodyValue(requestBody)
        .retrieve()
        .toBodilessEntity()
        .onErrorResume(error -> {
          if (error instanceof WebClientResponseException) {
            WebClientResponseException exception = (WebClientResponseException) error;
            if (exception.getStatusCode().is4xxClientError()) {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Logout failed");
            }
          }
          return Mono.error(error);
        })
        .block();

    log.info("Successfully logged out user with refresh token: {}", refreshToken);
    return ResponseEntity.noContent().build();
  }


  private MultiValueMap<String, String> buildLogoutRequestBody(String refreshToken) {
    var body = new LinkedMultiValueMap<String, String>();
    body.add("client_id", keycloakConfiguration.getClientId());
    body.add("client_secret", keycloakConfiguration.getClientSecret());
    body.add("refresh_token", refreshToken);
    body.add("accessToken", ""); // Add an empty value for the accessToken field
    log.debug("Built logout request body for refresh token: {}", refreshToken);
    return body;
  }

}
