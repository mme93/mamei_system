package com.user.repository;

import com.user.model.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {
    Optional<SystemUserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
    void deleteByUserName(String userName);
}
