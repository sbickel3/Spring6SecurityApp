package com.spring.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.security")
public class MvcConfig implements WebMvcConfigurer {

	   @Bean
	    public ObjectMapper objectMapper() {
	        // Customize Jackson if needed
	        return new ObjectMapper();
	    }

	    @Bean
	    public MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
	        return new MappingJackson2HttpMessageConverter(objectMapper);
	    }

	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(jacksonMessageConverter(objectMapper()));
	    }
}