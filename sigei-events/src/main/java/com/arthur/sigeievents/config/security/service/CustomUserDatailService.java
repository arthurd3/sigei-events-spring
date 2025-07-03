package com.arthur.sigeievents.config.security.service;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDatailService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userEntity = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));


        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userEntity.getUserType().name());

        return new UserLogged(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getPoints(),
                List.of(authority)
        );
    }
}
