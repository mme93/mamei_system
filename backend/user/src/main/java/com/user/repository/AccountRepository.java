package com.user.repository;

import com.user.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity>findByIdAndUserId(Long id, Long userId);
}
