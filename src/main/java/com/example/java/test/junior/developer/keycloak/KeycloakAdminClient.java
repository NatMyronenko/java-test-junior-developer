package com.example.java.test.junior.developer.keycloak;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakAdminClient {

  private final KeycloakConfiguration keycloakProperties;
  private final Keycloak keycloak;

  public void createUser(UserRepresentation user) {
    log.info("Creating user with username: {}", user.getUsername());
    keycloak.realm(keycloakProperties.getRealm()).users().create(user);
    log.info("User created successfully");
  }
}