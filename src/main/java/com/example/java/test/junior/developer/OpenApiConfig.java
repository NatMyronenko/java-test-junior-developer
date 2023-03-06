package com.example.java.test.junior.developer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Order(Ordered.LOWEST_PRECEDENCE)
public class OpenApiConfig {
    @Bean
    public Docket swaggerApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example"))
                .build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        System.out.println("*** corsConfigurer called");
        return new WebMvcConfigurerAdapter() {
            @Override public void addCorsMappings(CorsRegistry registry) {
                System.out.println("*** addCorsMappings called");
                registry.addMapping("/v2/api-docs");
            }
        };
    }
}

