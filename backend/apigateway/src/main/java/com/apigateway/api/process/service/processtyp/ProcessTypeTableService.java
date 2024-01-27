package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.database.service.domains.ProcessDefaultDBService;
import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.api.process.model.ProcessDefaultNameTable;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypeTableService implements IProcessTypeService{

    private final ProcessDefaultDBService processDefaultDBService;

    @Autowired
    public ProcessTypeTableService(ProcessDefaultDBService processDefaultDBService) {
        this.processDefaultDBService = processDefaultDBService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        return switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
    }

    @Override
    public boolean deleteProcess(ExecuteProcess process) {
        return true;
    }

    @Override
    public boolean resetProcess(ExecuteProcess process) {
        if(process.getProcessName().equals(ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET)){
            processDefaultDBService.deleteAllDefaultData();
            processDefaultDBService.test();
        }
        return false;
    }
}
