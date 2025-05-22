package com.novacoding.lumolearn_api.LumoLearn.API.security;

import java.io.IOException;
import java.security.Key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.novacoding.lumolearn_api.LumoLearn.API.config.SecurityDatabaseService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

  @Autowired
  private Key jwtSigningKey;

  // injete seu Service que implementa UserDetailsService:
  @Autowired
  private SecurityDatabaseService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
        throws ServletException, IOException {

    String header = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
    if (header != null && header.startsWith(SecurityConfig.PREFIX)) {
      try {
    	String jwt = header.substring(SecurityConfig.PREFIX.length()).trim();
        
    	JWTObject tokenObject = JWTCreator.parseToken(jwt, SecurityConfig.PREFIX, jwtSigningKey);

        // Recarrega o usuário do banco:
        UserDetails userDetails = userDetailsService
                                    .loadUserByUsername(tokenObject.getSubject());

        // Cria o Authentication com *todas* as authorities:
        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );

        SecurityContextHolder.getContext().setAuthentication(auth);

      } catch (ExpiredJwtException | UnsupportedJwtException |
               MalformedJwtException | SignatureException e) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return;
      }
    } else {
      SecurityContextHolder.clearContext();
    }

    filterChain.doFilter(request, response);
  }
}