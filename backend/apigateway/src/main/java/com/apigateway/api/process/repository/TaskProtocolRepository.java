package com.apigateway.api.process.repository;

import com.apigateway.api.process.model.protocol.TaskProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskProtocolRepository extends JpaRepository<TaskProtocol, Long> {
}
