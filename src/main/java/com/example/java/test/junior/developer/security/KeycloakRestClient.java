package com.example.java.test.junior.developer.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
@Slf4j
public class KeycloakRestClient {

  private final KeycloakConfiguration keycloakConfig;
  private final WebClient webClient;

  public void logout(String refreshToken, String accessToken) {
    MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    formData.add("client_id", keycloakConfig.getClientId());
    formData.add("client_secret", keycloakConfig.getClientSecret());
    formData.add("refresh_token", refreshToken);

    log.debug("Sending logout request to Keycloak with refresh token: {}", refreshToken);

    webClient.post()
        .uri("/realms/{realm}/protocol/openid-connect/logout", keycloakConfig.getRealm())
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
        .body(BodyInserters.fromFormData(formData))
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

    log.info("Successfully logged out user");
  }
}


//  public void logout(String refreshToken, String accessToken) {
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//    headers.setBearerAuth(accessToken);
//
//    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//    map.add("client_id", keycloakConfig.getClientId());
//    map.add("client_secret", keycloakConfig.getClientSecret());
//    map.add("refresh_token", refreshToken);
//
//    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//    String url = keycloakConfig.getAuthServerUrl() + "/realms/" + keycloakConfig.getRealm() +
//        "/protocol/openid-connect/logout";
//
//    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//    if (!response.getStatusCode().is2xxSuccessful()) {
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Logout failed");
//    }
//  }
//}