package com.kaushik.mergeassignment.services;

import com.kaushik.mergeassignment.entities.UserEntity;
import com.kaushik.mergeassignment.mappers.UserMapper;
import com.kaushik.mergeassignment.models.Role;
import com.kaushik.mergeassignment.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public boolean belongsToUser(Authentication authentication, Long id) {
        return userRepository.findByUsername(authentication.getName())
                .map(user -> !user.getId().equals(id))
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameAndEnabledIsTrue(username)
                .map(com.kaushik.mergeassignment.models.LoggedInUser::new)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Incorrect username or password"));

    }

    @Transactional
    public UserEntity createUser(String username, String encodedPassword, String firstName, String lastName, @NotNull Role role) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User already exists");
                });
        UserEntity user = UserEntity.builder()
                .credExpired(false)
                .enabled(true)
                .role(role)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(encodedPassword)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity promoteUser(Long id) {
        UserEntity user = getUser(id);
        user.setRole(Role.ADMIN);
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity suspendUser(Long id) {
        UserEntity user = getUser(id);
        user.setEnabled(false);
        return userRepository.save(user);
    }

    private UserEntity getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
