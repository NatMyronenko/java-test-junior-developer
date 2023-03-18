package com.example.java.test.junior.developer.security;

import lombok.Setter;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//TODO: Розібратись з ConfigurationProperties (як діставати дані). username ?
@Setter
@Configuration
// не зовсім розібрався як штука нижче працює (автоматично підставляє через setter?), витягнув дані з keycloakProperties
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakConfiguration {
    private String clientName;
    private KeycloakSpringBootProperties keycloakProperties; // Чи можна використати для введення даних?

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getAuthServerUrl())  // Ще не придумав як краще брати налаштування з properties
                .realm(keycloakProperties.getRealm())              // те саме (налаштовується в Keycloak наскільки я зрозумів)
                .clientId(keycloakProperties.getResource())        // можливо непотрібне поле =)
               // .clientSecret() // Думаю що це не потрібно
                .username(clientName)    // логін від Keycloak. Поки не знайшов де він вноситься
                .password(keycloakProperties.getClientKeyPassword())    // пароль від нього ж
                .build();
    }
}