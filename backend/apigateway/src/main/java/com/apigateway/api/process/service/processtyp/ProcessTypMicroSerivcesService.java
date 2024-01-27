package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.ExecuteProcess;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypMicroSerivcesService {
    public boolean executeProcess(ExecuteProcess process) {
        System.err.println("Ich bin ein MicroServices");
        return true;
    }
}
