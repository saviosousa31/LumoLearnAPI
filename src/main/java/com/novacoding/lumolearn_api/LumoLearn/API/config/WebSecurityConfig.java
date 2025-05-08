package com.novacoding.lumolearn_api.LumoLearn.API.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private SecurityDatabaseService securityDatabaseService; // implementa UserDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/api/courses/**").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/subjects/**").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/users").hasRole("ADMINS")
                .requestMatchers("/api/users/search").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/users/delete").hasAnyRole("USERS","ADMINS")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults()); // habilita HTTP Basic Authentication;

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Usa implicitamente o UserDetailsService (SecurityDatabaseService) e o PasswordEncoder definidos
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder para compatibilidade (não seguro em produção) 
    	return NoOpPasswordEncoder.getInstance();
    }
}