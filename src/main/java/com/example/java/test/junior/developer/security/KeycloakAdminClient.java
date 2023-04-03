package com.example.java.test.junior.developer.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KeycloakAdminClient {

  private final KeycloakConfiguration keycloakProperties;
  private final Keycloak keycloak;

  private static final Logger logger = LoggerFactory.getLogger(KeycloakAdminClient.class);

  public void createUser(UserRepresentation user) {
    logger.info("Creating user with username: {}", user.getUsername());
    keycloak.realm(keycloakProperties.getRealm()).users().create(user);
    logger.info("User created successfully");
  }
}