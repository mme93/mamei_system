package com.apigateway.api.process.service;

import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.repository.ProcessRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;
    private final ProcessTypDatabaseService processTypDatabaseService;
    private final ProcessTypMicroSerivcesService processTypMicroSerivcesService;

    @Autowired
    public ProcessService(ProcessRepository processRepository, ProcessTypDatabaseService processTypDatabaseService, ProcessTypMicroSerivcesService processTypMicroSerivcesService) {
        this.processRepository = processRepository;
        this.processTypDatabaseService = processTypDatabaseService;
        this.processTypMicroSerivcesService = processTypMicroSerivcesService;
    }

    public boolean startProcess(ExecuteProcess process) {

        switch (process.getProcessTyp()) {
            case DATABASE:
                return processTypDatabaseService.executeProcess(process);
            case MICRO_SERVICES:
                return processTypMicroSerivcesService.executeProcess(process);
            default:
                throw new NotFoundException("No Process Typ found by Name: " + process.getProcessTyp());
        }
    }


    public List<Process> getProcesses() {
        return processRepository.findAll();
    }

    public Object sortProcessList(List<Process> processList) {
        return processList;
    }
}
