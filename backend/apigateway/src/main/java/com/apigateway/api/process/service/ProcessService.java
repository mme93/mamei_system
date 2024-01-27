package com.apigateway.api.process.service;

import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.repository.ProcessRepository;
import com.apigateway.api.process.service.processtyp.ProcessTypDatabaseService;
import com.apigateway.api.process.service.processtyp.ProcessTypMicroServicesService;
import com.apigateway.api.process.service.processtyp.ProcessTypeDataSetService;
import com.apigateway.api.process.service.processtyp.ProcessTypeTableService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;
    private final ProcessTypDatabaseService processTypDatabaseService;
    private final ProcessTypMicroServicesService processTypMicroServicesService;
    private final ProcessTypeDataSetService processTypeDataSetService;
    private final ProcessTypeTableService processTypeTableService;

    @Autowired
    public ProcessService(ProcessRepository processRepository, ProcessTypDatabaseService processTypDatabaseService, ProcessTypMicroServicesService processTypMicroServicesService, ProcessTypeDataSetService processTypeDataSetService, ProcessTypeTableService processTypeTableService) {
        this.processRepository = processRepository;
        this.processTypDatabaseService = processTypDatabaseService;
        this.processTypMicroServicesService = processTypMicroServicesService;
        this.processTypeDataSetService = processTypeDataSetService;
        this.processTypeTableService = processTypeTableService;
    }

    public boolean startProcess(ExecuteProcess process) {

        switch (process.getProcessTyp()) {
            case DATABASE:
                return processTypDatabaseService.executeProcess(process);
            case MICRO_SERVICES:
                return processTypMicroServicesService.executeProcess(process);
            case DATA_SET:
                return processTypeDataSetService.executeProcess(process);
            case TABLE:
                return processTypeTableService.executeProcess(process);
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
