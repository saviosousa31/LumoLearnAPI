package com.novacoding.lumolearn_api.LumoLearn.API.controller;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novacoding.lumolearn_api.LumoLearn.API.dto.Login;
import com.novacoding.lumolearn_api.LumoLearn.API.dto.Session;
import com.novacoding.lumolearn_api.LumoLearn.API.model.User;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.UserRepository;
import com.novacoding.lumolearn_api.LumoLearn.API.security.JWTCreator;
import com.novacoding.lumolearn_api.LumoLearn.API.security.JWTObject;
import com.novacoding.lumolearn_api.LumoLearn.API.security.SecurityConfig;

@RestController
public class LoginController {
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private Key jwtSigningKey;

    @PostMapping("/login")
    public Session userActiveSession(@RequestBody Login login) {
        // 1) Busca o usuário e valida
        User user = userRepository
                        .findByUsername(login.getUsername())
                        .orElseThrow(() -> 
                            new RuntimeException("Usuário não encontrado: " + login.getUsername())
                        );

        if (!encoder.matches(login.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
        }

        // 2) Monta o objeto de sessão
        Session session = new Session();
        session.setLogin(user.getUsername());

        // 3) Cria o JWTObject e PIENE TODOS os campos
        JWTObject jwtObject = new JWTObject();
        jwtObject.setSubject(user.getUsername());               // **NÃO** esqueça do subject!
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));

        // 4) Pega as roles do seu modelo User (ajuste conforme seu getRoles)
        //    Supondo que user.getRoles() retorne Set<Role> com getRole_name().
        jwtObject.setRoles(
            user.getRoles().stream()
                .map(roleEntity -> roleEntity.getRole_name())    // ex.: "USERS", "ADMINS"
                .collect(Collectors.toList())
        );

        // 5) Gera o token (irá chamar seu método que coloca prefixo "Bearer ")
        String token = JWTCreator.generateToken(SecurityConfig.PREFIX, jwtSigningKey, jwtObject);
        session.setToken(token);

        return session;
    }
}
