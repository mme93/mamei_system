package com.apigateway.security;

import com.apigateway.security.model.entity.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserEntityRepository extends JpaRepository<SecurityUserEntity, Long> {

    Optional<SecurityUserEntity> findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}
