package com.systemmanager.process.repository;

import com.systemmanager.process.model.process.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    boolean existsByProcessName(String processName);
}
