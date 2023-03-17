package com.example.java.test.junior.developer.security;

import lombok.Data;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Bean;

@Data
public class KeycloakAdminClient {
    private final Keycloak keycloak;

    @Bean
    public void createUser(String realm, UserRepresentation user) {
        // Додавання user до Keycloak
        keycloak.realm(realm).users().create(user);
    }
}