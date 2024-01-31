package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.database.service.domains.ProcessDefaultDBService;
import com.apigateway.api.process.model.process.ExecuteProcess;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypDatabaseService{

    private final ProcessDefaultDBService processDefaultDBService;

    @Autowired
    public ProcessTypDatabaseService(ProcessDefaultDBService processDefaultDBService) {
        this.processDefaultDBService = processDefaultDBService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        switch (process.getProcessEvent()) {
            case DELETE:
                return deleteProcess(process);
            case ADD:
                return addProcess(process);
            case CREATE:
                return createProcess(process);
            case RESET:
                return resetProcess(process);
            default:
                throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        }
    }

    private boolean resetProcess(ExecuteProcess process) {
        return true;
    }

    public boolean deleteProcess(ExecuteProcess process) {

        return true;
    }

    public boolean addProcess(ExecuteProcess process) {

        return true;
    }

    public boolean createProcess(ExecuteProcess process) {

        return true;
    }
}
