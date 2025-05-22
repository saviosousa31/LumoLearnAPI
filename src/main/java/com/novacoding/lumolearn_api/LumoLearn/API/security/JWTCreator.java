package com.novacoding.lumolearn_api.LumoLearn.API.security;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//JWTCreator.java
public class JWTCreator {
 public static final String HEADER_AUTHORIZATION = "Authorization";
 private static final String ROLES = "authorities";

 // 1) geração
 public static String generateToken(String prefix, Key key, JWTObject obj) {
     if (obj.getSubject() == null) {
         throw new IllegalArgumentException("subject não pode ser nulo");
     }
     String jwt = Jwts.builder()
         .setSubject(obj.getSubject())
         .setIssuedAt(obj.getIssuedAt())
         .setExpiration(obj.getExpiration())
         .claim(ROLES, obj.getRoles())
         .signWith(key, SignatureAlgorithm.HS512)
         .compact();
     return prefix + " " + jwt;
 }

 // 2) parsing
 public static JWTObject parseToken(String header, String prefix, Key key)
         throws ExpiredJwtException, UnsupportedJwtException,
                MalformedJwtException, SignatureException {

     if (header == null) {
         throw new IllegalArgumentException("header inválido");
     }
     Claims claims = Jwts.parser()
                         .setSigningKey(key)
                         .parseClaimsJws(header)
                         .getBody();

     JWTObject obj = new JWTObject();
     obj.setSubject(claims.getSubject());      // agora não será null
     obj.setIssuedAt(claims.getIssuedAt());
     obj.setExpiration(claims.getExpiration());     
     List<String> roles = (List<String>) claims.get("authorities");
     obj.setRoles(roles);
     return obj;
 }
}
