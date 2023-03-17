package com.example.java.test.junior.developer.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Arrays;

public class KeycloakAdminClient {
    private Keycloak keycloak;

    public void createUser() {

        /* Параметри підключення до Keycloak сервера - створені для візуалізацї
         *(будуть предаватись аргументами в білдер) Спробую їх імпортувати з пропертей
         * використовуючи @Component та @Value
         */
        String serverUrl = "http://localhost:8080/auth";
        String realm = "myrealm";
        String clientId = "myclient";
        String clientSecret = "mysecret";
        String username = "myusername";
        String password = "mypassword";

        // Створення Keycloak клієнта
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)  // Ще не придумав як краще брати налаштування з properties
                .realm(realm)          // те саме (налаштовується в Keycloak наскільки я зрозумів)
                .clientId(clientId)    // можливо непотрібне поле =)
                .clientSecret(clientSecret) // також з пропертя
                .username(username)    // логін від Keycloak
                .password(password)    // пароль від нього ж
                .build();              // створити

        // Створення user-а виніс в UserMapper
        UserRepresentation user ;

        // Встановлення пароля для user
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("password");
        credential.setTemporary(false);
        user.setCredentials(Arrays.asList(credential));

        // Додавання user до Keycloak
        keycloak.realm(realm).users().create(user);
    }
}