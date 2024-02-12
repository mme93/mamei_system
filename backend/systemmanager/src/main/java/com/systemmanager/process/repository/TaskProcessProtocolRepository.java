package com.systemmanager.process.repository;

import com.systemmanager.process.model.protocol.TaskProcessProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskProcessProtocolRepository extends JpaRepository<TaskProcessProtocol, Long> {
    Optional<TaskProcessProtocol>findBySignature(String signature);
}
