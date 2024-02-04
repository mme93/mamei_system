package com.apigateway.api.process.service;

import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.process.Process;
import com.apigateway.api.process.model.ui.ExecuteProcessUI;
import com.apigateway.api.process.model.ui.ProcessElementUI;
import com.apigateway.api.process.repository.ProcessRepository;
import com.apigateway.api.process.service.factory.ExecuteProcessFactory;
import com.apigateway.api.process.service.processtyp.ProcessTypDatabaseService;
import com.apigateway.api.process.service.processtyp.ProcessTypMicroServicesService;
import com.apigateway.api.process.service.processtyp.ProcessTypeDataSetService;
import com.apigateway.api.process.service.processtyp.ProcessTypeTableService;
import com.apigateway.api.process.service.protocol.ProcessProtocolService;
import com.apigateway.util.LocalDateTimeFactory;
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
    private final ProcessRuleService processRuleService;
    private final ProcessProtocolService protocolService;
    private final LocalDateTimeFactory localDateTimeFactory;

    @Autowired
    public ProcessService(ProcessRepository processRepository, ProcessTypDatabaseService processTypDatabaseService, ProcessTypMicroServicesService processTypMicroServicesService, ProcessTypeDataSetService processTypeDataSetService, ProcessTypeTableService processTypeTableService, ExecuteProcessFactory processFactory, ProcessRuleService processRuleService, ProcessProtocolService protocolService, LocalDateTimeFactory localDateTimeFactory) {
        this.processRepository = processRepository;
        this.processTypDatabaseService = processTypDatabaseService;
        this.processTypMicroServicesService = processTypMicroServicesService;
        this.processTypeDataSetService = processTypeDataSetService;
        this.processTypeTableService = processTypeTableService;
        this.processFactory = processFactory;
        this.processRuleService = processRuleService;
        this.protocolService = protocolService;
        this.localDateTimeFactory = localDateTimeFactory;
    }

    public boolean startProcess(ExecuteProcess process) {
        if (processRuleService.isProcedure(process)) {
            return protocolService.createProcessProtocol(true, process, localDateTimeFactory.generateLocalTimeDate(), "");
        }
        String start = localDateTimeFactory.generateLocalTimeDate();
        boolean isPassed;
        switch (process.getProcessTyp()) {
            case DATABASE:
                isPassed = processTypDatabaseService.executeProcess(process);
                break;
            case MICRO_SERVICES:
                isPassed = processTypMicroServicesService.executeProcess(process);
                break;
            case DATA_SET:
                isPassed = processTypeDataSetService.executeProcess(process);
                break;
            case TABLE:
                isPassed = processTypeTableService.executeProcess(process);
                break;
            default:
                throw new NotFoundException("No Process Typ found by Name: " + process.getProcessTyp());
        }
        return protocolService.createProcessProtocol(isPassed, process, start, localDateTimeFactory.generateLocalTimeDate());
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
