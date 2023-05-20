package com.example.java.test.junior.developer.keycloak;

import javax.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakAdminClient {

  private final KeycloakConfiguration keycloakProperties;
  private final Keycloak keycloak;

  public void createUser(UserRepresentation user) {
    log.info("Creating user with username: {}", user.getUsername());
    var response = keycloak.realm(keycloakProperties.getRealm()).users().create(user);
    int statusCode = response.getStatus();
    if (statusCode == Response.Status.CREATED.getStatusCode()) {
      log.info("User created successfully with status code: {}", statusCode);
    } else {
      log.error("User creation failed with status code: {}", statusCode);
      try {
        throw new RuntimeException("User creation failed");
      } catch (RuntimeException e) {
        throw new ResponseStatusException(HttpStatus.valueOf(statusCode));
      }
    }
  }
}