package com.kaushik.mergeassignment.repositories;

import com.kaushik.mergeassignment.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndEnabledIsTrue(String username);

    Optional<UserEntity> findByUsername(String username);
}
