package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
 	public InMemoryUserDetailsManager userDetailsService() {
 		UserDetails user = User
 			.withUsername("user")
 			.password("{noop}password_user")
 			.roles("USER")
 			.build();
 		UserDetails admin = User
 			.withUsername("admin")
 			.password("{noop}password_admin")
 			.roles("ADMIN")
 			.build();
 		return new InMemoryUserDetailsManager(user, admin);
 	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((request) -> request
                .requestMatchers("/welcome").permitAll()
                .anyRequest().authenticated())
            .formLogin(form -> form
            	        .defaultSuccessUrl("/welcome")
            	        .permitAll())
            .logout(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

