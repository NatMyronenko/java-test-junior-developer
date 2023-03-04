package com.example.java.test.junior.developer.controllers;

import com.example.java.test.junior.developer.services.UserService;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 */

@Configuration
public class Controller {

    private final UserService userService = new UserService();

    /**
     *
     * @return DispatcherServlet new object.
     *
     * @see DispatcherServlet
     */

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    /**
     *
     * @return ServletRegistrationBean
     *
     * @see ServletRegistrationBean
     * @see DispatcherServletAutoConfiguration
     */

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet(), "all /api/v1/users");

        registration.
                setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

}
