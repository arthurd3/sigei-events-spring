package com.arthur.sigeievents.config.security.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class UserLogged implements UserDetails{

    private final Long id;
    private final String username;
    private final String password;
    private final int points;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserLogged(Long id, String username, String password, int points , Collection<? extends GrantedAuthority> authorities ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.points = points;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }

}
