package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
 	public InMemoryUserDetailsManager userDetailsService() {
 		UserDetails user = User.withDefaultPasswordEncoder()
 			.username("user")
 			.password("password_user")
 			.roles("USER")
 			.build();
 		UserDetails admin = User.withDefaultPasswordEncoder()
 			.username("admin")
 			.password("password_admin")
 			.roles("ADMIN")
 			.build();
 		return new InMemoryUserDetailsManager(user, admin);
 	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((request) -> request
                .requestMatchers("/Welcome").permitAll()
                .requestMatchers("/User").hasAnyRole("USER","ADMIN")
                .requestMatchers("/Admin").hasRole("ADMIN")
                .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

