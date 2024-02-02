package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.service.protocol.ProcessProtocolService;
import com.apigateway.microservice.restart.service.MicroServicesRestartService;
import com.apigateway.util.LocalDateTimeFactory;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessTypMicroServicesService implements IProcessTypeService {

    private final MicroServicesRestartService microServicesRestartService;
    private final ProcessProtocolService protocolService;
    private final LocalDateTimeFactory localDateTimeFactory;

    @Autowired
    public ProcessTypMicroServicesService(MicroServicesRestartService microServicesRestartService, ProcessProtocolService protocolService, LocalDateTimeFactory localDateTimeFactory) {
        this.microServicesRestartService = microServicesRestartService;
        this.protocolService = protocolService;
        this.localDateTimeFactory = localDateTimeFactory;
    }

    public boolean executeProcess(ExecuteProcess process) {
        String start = localDateTimeFactory.generateLocalTimeDate();
        boolean isSuccessful = switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
        return protocolService.createProcessProtocol(isSuccessful, process, start, localDateTimeFactory.generateLocalTimeDate());
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
