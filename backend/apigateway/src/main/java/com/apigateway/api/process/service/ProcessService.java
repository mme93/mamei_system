package com.apigateway.api.process.service;

import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.repository.ProcessRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public boolean startProcess(Process process) {

        switch (process.getProcessTyp()) {
            case EUREKA_SYSTEM:
                return startProcessForEurekaSystem();
            case DATABASE:
                return startProcessForDatabase();
            case MICRO_SERVICES:
                return startProcessForMicroServices();
            default:
                throw new NotFoundException("No Process Typ found by Name: " + process.getProcessTyp());
        }
    }

    public boolean startProcessForDatabase() {
        return true;
    }

    public boolean startProcessForMicroServices() {
        return true;
    }

    public boolean startProcessForEurekaSystem() {
        return true;
    }

    public List<Process> getProcesses() {
        return processRepository.findAll();
    }

    public Object sortProcessList(List<Process> processList) {
        return processList;
    }
}
