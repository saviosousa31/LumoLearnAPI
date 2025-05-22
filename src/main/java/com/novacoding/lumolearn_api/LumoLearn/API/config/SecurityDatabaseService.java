package com.novacoding.lumolearn_api.LumoLearn.API.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.User;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.UserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userEntity = userRepository.findByUsername(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        userEntity.get().getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole_name()));
        });
        
        UserDetails user = new org.springframework.security.core.userdetails.User(
        		userEntity.get().getUsername(),
                userEntity.get().getPassword(),
                authorities);
        return user;
    }
}
