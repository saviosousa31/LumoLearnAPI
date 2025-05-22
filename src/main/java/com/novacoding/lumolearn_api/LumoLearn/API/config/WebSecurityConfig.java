package com.novacoding.lumolearn_api.LumoLearn.API.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.novacoding.lumolearn_api.LumoLearn.API.security.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig { 
    @Autowired
    private SecurityDatabaseService securityDatabaseService; 
    @Autowired
    private JWTFilter jwtFilter;  
    
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authProvider) throws Exception {
        http
        	.csrf(csrf -> csrf.disable())
            .authenticationProvider(authProvider)   
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/webjars/**"
                    ).permitAll()
                .requestMatchers(HttpMethod.POST,"/login").permitAll()
                .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/courses").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/courses/**").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/subjects/**").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/users").hasRole("ADMINS")
                .requestMatchers("/api/users/search").hasAnyRole("USERS","ADMINS")
                .requestMatchers("/api/users/delete").hasAnyRole("USERS","ADMINS")
                .anyRequest().authenticated()             
            );
        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        // Usa implicitamente o UserDetailsService (SecurityDatabaseService) e o PasswordEncoder definidos
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", bcrypt);
        DelegatingPasswordEncoder delegating =
            new DelegatingPasswordEncoder("bcrypt", encoders);
        delegating.setDefaultPasswordEncoderForMatches(bcrypt);
        return delegating;
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityDatabaseService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}