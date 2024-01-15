package com.apigateway.status.microservice.repository;

import com.apigateway.status.microservice.model.entity.MicroServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MicroServiceRepository extends JpaRepository<MicroServiceEntity, Long> {
    Optional<MicroServiceEntity>findByEurekaServiceName(String eurekaServiceName);
    boolean existsByEurekaServiceName(String eurekaServiceName);
}
