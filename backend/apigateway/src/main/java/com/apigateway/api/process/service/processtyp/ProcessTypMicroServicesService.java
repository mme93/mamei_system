package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.service.protocol.ProcessProtocolService;
import com.apigateway.microservice.restart.service.MicroServicesRestartService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessTypMicroServicesService implements IProcessTypeService {

    private final MicroServicesRestartService microServicesRestartService;
    private final ProcessProtocolService protocolService;

    @Autowired
    public ProcessTypMicroServicesService(MicroServicesRestartService microServicesRestartService, ProcessProtocolService protocolService) {
        this.microServicesRestartService = microServicesRestartService;
        this.protocolService = protocolService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        LocalDateTime start = LocalDateTime.now();
        boolean isSuccessful = switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
        LocalDateTime end = LocalDateTime.now();
        return protocolService.createProcessProtocol(isSuccessful, process, start, end);
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
        microServicesRestartService.restartService(process.getTheme());
        return true;
    }
}
