package com.example.java.test.junior.developer.configurations;

@RunWith(MockitoJUnitRunner.class)
public class SecurityConfigTest {

  @Mock
  private RestTemplate restTemplate;

  @Value("${external.api.url}")
  private String externalApiUrl;

  @InjectMocks
  private SecurityConfig securityConfig;

  @Mock
  private HttpSecurity http;

  @Test
  public void configure_ShouldDisableCsrf() throws Exception {
    // Arrange

    // Act
    securityConfig.configure(http);

    // Assert
    verify(http).csrf().disable();
  }

  @Test
  public void configure_ShouldAuthorizeRequests() throws Exception {
    // Arrange

    // Act
    securityConfig.configure(http);

    // Assert
    verify(http).authorizeRequests();
  }

  @Test
  public void configure_ShouldAuthorizeApiRequests() throws Exception {
    // Arrange
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = Mockito
        .mock(ExpressionUrlAuthorizationConfigurer.AuthorizedUrl.class);
    when(http.authorizeRequests().antMatchers("/api/**")).thenReturn(authorizedUrl);

    // Act
    securityConfig.configure(http);

    // Assert
    verify(authorizedUrl).authenticated();
  }

  @Test
  public void configure_ShouldAddJwtAuthorizationFilter() throws Exception {
    // Arrange
    JwtAuthorizationFilter jwtAuthorizationFilter = Mockito.mock(JwtAuthorizationFilter.class);
    whenNew(JwtAuthorizationFilter.class).withArguments(restTemplate, externalApiUrl)
        .thenReturn(jwtAuthorizationFilter);

    // Act
    securityConfig.configure(http);

    // Assert
    verify(http).addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
