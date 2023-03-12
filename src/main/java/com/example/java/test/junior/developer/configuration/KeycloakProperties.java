package com.example.java.test.junior.developer.configuration;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KeycloakProperties contain a KeycloakConfiguration information.
 *
 * @see Keycloak
 */

@Configuration
@ConfigurationProperties
public class KeycloakProperties {

    /**
     *
     * @return Keycloak data type (serverUrl, realm, clientId and clientSecret).
     *
     * @see KeycloakBuilder
     */

    @Bean
    Keycloak keycloakConfiguration() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080")
                .realm("Test_Realm")
                .clientId("test_client")
                .clientSecret("f3wVWQmvwQF8dM2wC96brWLMpQbiQLV1")
                .username("test_client")
                .password("testPassword")
                .build();
    }
}