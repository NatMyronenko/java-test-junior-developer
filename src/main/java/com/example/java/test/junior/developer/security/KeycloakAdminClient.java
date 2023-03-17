package com.example.java.test.junior.developer.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class KeycloakAdminClient {
    @Autowired
    private Keycloak keycloak;

    @Bean
    public void createUser(String realm, UserRepresentation user) {
        // Додавання user до Keycloak
        keycloak.realm(realm).users().create(user);
    }
}