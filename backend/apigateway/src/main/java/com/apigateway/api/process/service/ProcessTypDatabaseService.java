package com.apigateway.api.process.service;

import com.apigateway.api.database.service.domains.ProcessDefaultDBService;
import com.apigateway.api.process.model.ExecuteProcess;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypDatabaseService {

    private final ProcessDefaultDBService processDefaultDBService;

    @Autowired
    public ProcessTypDatabaseService(ProcessDefaultDBService processDefaultDBService) {
        this.processDefaultDBService = processDefaultDBService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        switch (process.getProcessEvent()) {
            case DELETE:
                return deleteProcess(process);
            case CREATE:
                return createProcess(process);
            default:
                throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        }
    }

    public boolean deleteProcess(ExecuteProcess process) {
        if(process.getProcessName().equals("CLEAR_TABLE")){
            processDefaultDBService.deleteAllDefaultData();
        }
        return true;
    }

    public boolean createProcess(ExecuteProcess process) {

        return true;
    }
}
