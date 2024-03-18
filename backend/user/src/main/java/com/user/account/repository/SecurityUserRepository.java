package com.user.account.repository;


import com.user.account.model.entity.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing security user entities.
 */
public interface SecurityUserRepository extends JpaRepository<SecurityUserEntity, Long> {
}
