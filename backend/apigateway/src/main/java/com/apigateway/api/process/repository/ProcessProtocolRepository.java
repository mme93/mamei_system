package com.apigateway.api.process.repository;

import com.apigateway.api.process.model.protocol.ProcessProtocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProcessProtocolRepository extends JpaRepository<ProcessProtocol, Long> {
    List<ProcessProtocol>findAllByParentSignature(String parentSignature);

}
