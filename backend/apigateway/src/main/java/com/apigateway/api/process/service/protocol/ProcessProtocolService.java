package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.repository.ProcessProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessProtocolService {

    private final ProcessProtocolRepository processProtocolRepository;

    @Autowired
    public ProcessProtocolService(ProcessProtocolRepository processProtocolRepository) {
        this.processProtocolRepository = processProtocolRepository;
    }

    public void createProcessProtocol(){

    }

}
