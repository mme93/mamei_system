package com.apigateway.api.process.repository;

import com.apigateway.api.process.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    boolean existsByProcessName(String processName);
}
