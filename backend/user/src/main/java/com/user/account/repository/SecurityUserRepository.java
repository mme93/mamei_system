package com.user.account.repository;


import com.user.account.model.entity.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUserEntity, Long> {
}
