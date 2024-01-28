package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.microservice.restart.service.MicroServicesRestartService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypMicroServicesService implements IProcessTypeService {

    private final MicroServicesRestartService microServicesRestartService;

    @Autowired
    public ProcessTypMicroServicesService(MicroServicesRestartService microServicesRestartService) {
        this.microServicesRestartService = microServicesRestartService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        return switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
    }

    @Override
    public boolean deleteProcess(ExecuteProcess process) {
        return false;
    }

    @Override
    public boolean resetProcess(ExecuteProcess process) {
        return false;
    }

    @Override
    public boolean restartProcess(ExecuteProcess process) {
        microServicesRestartService.restartService(process.getContext());
        return false;
    }
}
