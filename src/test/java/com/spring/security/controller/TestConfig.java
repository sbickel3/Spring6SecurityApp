package com.spring.security.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.security.config.SecurityConfig;

@Configuration
@EnableWebMvc
@Import(SecurityConfig.class) 
@ComponentScan(basePackages = "com.spring.security")
public class TestConfig {

	@Bean
    public UserPathsController userPathsController() {
        return new UserPathsController();
    }
}
