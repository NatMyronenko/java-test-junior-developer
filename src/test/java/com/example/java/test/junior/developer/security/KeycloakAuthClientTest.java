//package com.example.java.test.junior.developer.security;
//
//import static java.nio.charset.Charset.defaultCharset;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockserver.model.HttpRequest.request;
//import static org.mockserver.model.HttpResponse.response;
//import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
//
//import com.example.java.test.junior.developer.configuration.WebClientConfiguration;
//import com.preffero.user.management.configuration.KeycloakProperties;
//import com.preffero.user.management.configuration.
//    WebClientConfiguration;
//import com.preffero.user.management.request.LoginRequest;
//import com.preffero.user.management.request.RefreshTokenRequest;
//import java.io.IOException;
//import org.junit.jupiter.api.Test;
//import org.mockserver.client.MockServerClient;
//import org.mockserver.model.MediaType;
//import org.mockserver.springtest.MockServerTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.
//    EnableConfigurationProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.util.StreamUtils;
//
//@ActiveProfiles("test")
//@SpringBootTest(classes = {
//    KeycloakAuthClient.class,
//    WebClientConfiguration.class
//})
//@MockServerTest
//@EnableConfigurationProperties(KeycloakProperties.class)
//public class KeycloakAuthClientTest {
//
//  private MockServerClient mockServerClient;
//  @Autowired
//  private KeycloakAuthClient client;
//
//  @Test
//  void shouldReturnTokenFromKeycloak() throws IOException {
//    final var request = LoginRequest.builder()
//        .email("test@test.com")
//        .password("Pass@987")
//        .build();
//    final var requestStream =
//        new ClassPathResource("client/expected-keycloak-request.txt").
//            getInputStream();
//    final var expectedWebClientRequest = StreamUtils.copyToString
//        (requestStream, defaultCharset());
//    final var responseStream =
//        new ClassPathResource("client/keycloak-response.json").
//            getInputStream();
//    final var response = StreamUtils.copyToString(responseStream,
//        defaultCharset());
//    this.mockServerClient
//        .when(request()
//            .withMethod("POST")
//            .withPath("/realms/app/protocol/openid-connect/token")
//            .withHeader(CONTENT_TYPE, "application/x-www-form-
//                urlencoded; charset = UTF - 8 ")
//        .withBody(expectedWebClientRequest))
//.respond(response()
//        .withContentType(MediaType.APPLICATION_JSON)
//        .withBody(response));
//    final var actualResponse = client.generateToken(request);
//    assertThat(actualResponse).isNotNull();
//    assertThat(actualResponse.sessionId())
//        .isEqualTo("ccea4a55-9aec-4024-b11c-44f6f168439e");
//    assertThat(actualResponse.accessToken())
//        .isEqualTo("MTQ0NjJkZmQ5OTM2NDE1ZTZjNGZmZjI3");
//    assertThat(actualResponse.tokenType()).isEqualTo("Bearer");
//    assertThat(actualResponse.accessTokenExpiresInSeconds())
//        .isEqualTo("3600");
//    assertThat(actualResponse.refreshToken())
//        .isEqualTo("IwOGYzYTlmM2YxOTQ5MGE3YmNmMDFkNTVk");
//    assertThat(actualResponse.refreshTokenExpiresInSeconds())
//        .isEqualTo("21600");
//  }
//}