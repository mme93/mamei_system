package com.apigateway.api.process.service;

import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.model.ui.ExecuteProcessUI;
import com.apigateway.api.process.model.ui.ProcessElementUI;
import com.apigateway.api.process.repository.ProcessRepository;
import com.apigateway.api.process.service.factory.ExecuteProcessFactory;
import com.apigateway.api.process.service.processtyp.ProcessTypDatabaseService;
import com.apigateway.api.process.service.processtyp.ProcessTypMicroServicesService;
import com.apigateway.api.process.service.processtyp.ProcessTypeDataSetService;
import com.apigateway.api.process.service.processtyp.ProcessTypeTableService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;
    private final ProcessTypDatabaseService processTypDatabaseService;
    private final ProcessTypMicroServicesService processTypMicroServicesService;
    private final ProcessTypeDataSetService processTypeDataSetService;
    private final ProcessTypeTableService processTypeTableService;
    private final ExecuteProcessFactory processFactory;

    @Autowired
    public ProcessService(ProcessRepository processRepository, ProcessTypDatabaseService processTypDatabaseService, ProcessTypMicroServicesService processTypMicroServicesService, ProcessTypeDataSetService processTypeDataSetService, ProcessTypeTableService processTypeTableService, ExecuteProcessFactory processFactory) {
        this.processRepository = processRepository;
        this.processTypDatabaseService = processTypDatabaseService;
        this.processTypMicroServicesService = processTypMicroServicesService;
        this.processTypeDataSetService = processTypeDataSetService;
        this.processTypeTableService = processTypeTableService;
        this.processFactory = processFactory;
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

    public List<ProcessElementUI> getProcessElementUI() {
        List<ProcessElementUI> processElementUIList = new ArrayList<>();
        for (Process process : processRepository.findAll()) {
            processElementUIList.add(new ProcessElementUI(
                    process.getId(),
                    process.getProcessEvent(),
                    process.getProcessTyp(),
                    process.getProcessClassification(),
                    process.getProcessPlausibility(),
                    process.getProcessName(),
                    process.getProcessText(),
                    process.isHasDependedProcess(),
                    generateListFromString(process.getDependedProcessIds()),
                    generateListFromString(process.getScopes())
            ));
        }

        return processElementUIList;
    }

    public List<String> generateListFromString(String value) {
        if (value.contains("/")) {
            return asList();
        } else if (!value.contains(",")) {
            return asList(value);
        } else if (value.contains(",")) {
            return Arrays.stream(value.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        return asList(value.trim());

    }

    public ExecuteProcessUI createSortedExecuteProcessUI(List<ProcessElementUI> processList) {
        return processFactory.createExecuteProcessUI(processList);
    }
}
