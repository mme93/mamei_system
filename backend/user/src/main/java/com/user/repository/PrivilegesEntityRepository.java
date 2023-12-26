package com.user.repository;

import com.user.model.entity.PrivilegesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegesEntityRepository extends JpaRepository<PrivilegesEntity, Long> {
    Optional<PrivilegesEntity> findByAccountId(Long accountId);
}

