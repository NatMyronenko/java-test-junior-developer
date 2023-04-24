package com.example.java.test.junior.developer.keycloak;

import com.example.java.test.junior.developer.dto.LogOutRequestDto;
import com.example.java.test.junior.developer.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  private static final String CLIENT_ID_PARAM = "client_id";
  private static final String CLIENT_SECRET_PARAM = "client_secret";
  private static final String GRANT_TYPE_PARAM = "grant_type";

  private static final String TOKEN_PARAM = "token";
  private static final String REFRESH_TOKEN_PARAM = "refresh_token";

  private MultiValueMap<String, String> buildCommonRequestBody() {
    var body = new LinkedMultiValueMap<String, String>();
    body.add(CLIENT_ID_PARAM, keycloakConfiguration.getClientId());
    body.add(CLIENT_SECRET_PARAM, keycloakConfiguration.getClientSecret());
    body.add(GRANT_TYPE_PARAM, "password"); // by default, grant_type = password
    return body;
  }

  private MultiValueMap<String, String> buildLogoutRequestBody(String accessToken,
      String refreshToken) {
    var body = buildCommonRequestBody();
    body.add(TOKEN_PARAM, accessToken);
    body.add(REFRESH_TOKEN_PARAM, refreshToken);
    return body;
  }

  public LoginResponseDto getAccessToken(String username, String password) {
    var requestBody = buildCommonRequestBody();
    requestBody.add("username", username);
    requestBody.add("password", password);

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

  public void logout(String authorizationHeader, LogOutRequestDto requestDto) {
    var requestBody = buildLogoutRequestBody(requestDto.getAccessToken(),
        requestDto.getRefreshToken());

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

    log.info("Successfully logged out user with refresh token: {}", requestDto.getRefreshToken());
  }
}

