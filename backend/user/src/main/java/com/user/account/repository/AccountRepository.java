package com.user.account.repository;

import com.user.account.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity>findByIdAndUserId(Long id, Long userId);
    Optional<AccountEntity>findByUsername(String userName);
    Optional<AccountEntity>findByUserId(Long userId);
    boolean existsByUserId( Long userId);
}
