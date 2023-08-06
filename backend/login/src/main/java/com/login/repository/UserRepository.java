package com.login.repository;

import com.login.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Since email is unique, we'll find users by email
   // Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPassword(String password);
}
