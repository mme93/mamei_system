package com.systemmanager.process.service.processtyp;

import com.systemmanager.microservice.restart.service.MicroServicesRestartService;
import com.systemmanager.process.model.process.ExecuteProcess;
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
        return microServicesRestartService.restartService(process.getTheme());
    }
}
