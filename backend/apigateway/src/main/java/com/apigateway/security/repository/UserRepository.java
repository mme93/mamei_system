package com.apigateway.security.repository;


import com.apigateway.security.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByPassword(String password);
    Optional<UserEntity> findByUsername(String username);
}
