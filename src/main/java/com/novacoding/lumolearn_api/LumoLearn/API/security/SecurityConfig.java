package com.novacoding.lumolearn_api.LumoLearn.API.security;

import java.security.Key;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@ConfigurationProperties(prefix="security.config")
public class SecurityConfig {
	  
	public static String PREFIX;
	public static String KEY;
	public static Long EXPIRATION;
	
	public void setPrefix(String prefix) {
		PREFIX = prefix;
	}

	public void setKey(String key) {
		KEY = key;
	}

	public void setExpiration(Long expiration) {
		EXPIRATION = expiration;
	}
	
	@Bean
	public Key jwtSigningKey() {
	    return Keys.secretKeyFor(SignatureAlgorithm.HS512);
	}
}
