package com.novacoding.lumolearn_api.LumoLearn.API.controller;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

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
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Key jwtSigningKey;

    @PostMapping("/login")
    public Session login(@RequestBody Login login){
        Optional<User> user = userRepository.findByUsername(login.getUsername());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.get().getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Session session = new Session();
            session.setLogin(user.get().getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
//            jwtObject.setRoles(user.get().getRoles());
            jwtObject.setRoles(Arrays.asList("ADMINS", "USERS"));
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, jwtSigningKey, jwtObject));
            return session;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}