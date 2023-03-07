package com.example.java.test.junior.developer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
@EnableSwagger2
public class OpenApiConfigSwagger extends WebMvcConfigurerAdapter {
//    @Bean
//    public Docket swaggerApiConfig() {
//        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .paths(PathSelectors.any())
////                .apis(RequestHandlerSelectors.basePackage("com.example"))
////                .build();
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
@Bean
public Docket swaggerApiConfig() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .enableUrlTemplating(true)
            .ignoredParameterTypes(HttpServletRequest.class) // ignore HttpServletRequest parameter types
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, Arrays.asList(
                    new ResponseMessageBuilder()
                            .code(500)
                            .message("500 message")
                            .responseModel(new ModelRef("Error"))
                            .build(),
                    new ResponseMessageBuilder()
                            .code(403)
                            .message("Forbidden!")
                            .build()));
}
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}


