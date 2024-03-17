package com.user.repository;


import com.user.model.entity.SecurityUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityUserRepository extends JpaRepository<SecurityUserEntity, Long> {
}
