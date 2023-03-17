package com.example.java.test.junior.developer.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//TODO: Розібратись з ConfigurationProperties (як діставати дані). username ?
@Configuration
@ConfigurationProperties(prefix = "keycloak")   // не зовсім розібрався як ця штука працює (атоматично підставляє?), витягнув дані з keycloakProperties
public class KeycloakConfiguration {
    @Autowired
    private KeycloakSpringBootProperties keycloakProperties;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())  // Ще не придумав як краще брати налаштування з properties
                .realm(keycloakProperties.getRealm())          // те саме (налаштовується в Keycloak наскільки я зрозумів)
                .clientId(keycloakProperties.getResource())    // можливо непотрібне поле =)
               // .clientSecret() // Думаю що це не потрібно
                .username("admin")    // логін від Keycloak. Поки не знайшов де він вноситься
                .password(keycloakProperties.getClientKeyPassword())    // пароль від нього ж
                .build();
    }
}