package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.ExecuteProcess;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypMicroServicesService implements IProcessTypeService {
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

        return false;
    }
}
