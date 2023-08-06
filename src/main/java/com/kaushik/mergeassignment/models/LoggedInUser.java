package com.kaushik.mergeassignment.models;

import com.kaushik.mergeassignment.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoggedInUser implements UserDetails {

    private String username;
    private String password;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private List<GrantedAuthority> authorities;

    public LoggedInUser(UserEntity user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isCredentialsNonExpired = !user.isCredExpired();
        this.isEnabled = user.isEnabled();
        this.authorities = Stream.of(user.getRole())
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
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
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
