package com.systemmanager.microservice.status.repository;

import com.systemmanager.microservice.status.model.entity.MicroServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MicroServiceRepository extends JpaRepository<MicroServiceEntity, Long> {
    Optional<MicroServiceEntity>findByEurekaServiceName(String eurekaServiceName);
    boolean existsByEurekaServiceName(String eurekaServiceName);
}
