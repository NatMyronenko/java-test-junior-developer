package com.example.java.test.junior.developer.security;

import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakAdminClient {
    private final KeycloakSpringBootProperties keycloakProperties;
    private final Keycloak keycloak;

    public void createUser(UserRepresentation user) {
        keycloak.realm(keycloakProperties.getRealm()).users().create(user).close(); //should this be closed?
    }
}