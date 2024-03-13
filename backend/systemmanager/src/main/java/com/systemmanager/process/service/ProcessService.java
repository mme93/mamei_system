package com.systemmanager.process.service;

import com.systemmanager.process.model.process.ExecuteProcess;
import com.systemmanager.process.model.process.Process;
import com.systemmanager.process.model.ui.ExecuteProcessUI;
import com.systemmanager.process.model.ui.ProcessElementUI;
import com.systemmanager.process.repository.ProcessRepository;
import com.systemmanager.process.service.factory.ExecuteProcessFactory;
import com.systemmanager.process.service.processtyp.ProcessTypDatabaseService;
import com.systemmanager.process.service.processtyp.ProcessTypMicroServicesService;
import com.systemmanager.process.service.processtyp.ProcessTypeDataSetService;
import com.systemmanager.process.service.processtyp.ProcessTypeTableService;
import com.systemmanager.process.service.protocol.ProcessProtocolService;
import com.systemmanager.util.LocalDateTimeFactory;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Service class for managing processes.
 */
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

    /**
     * Starts a process.
     * @param process the process to start
     * @return true if the process started successfully, otherwise false
     */
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

    /**
     * Retrieves a list of process element UI.
     * @return a list of process element UI
     */
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

    /**
     * Generates a list of strings from a comma-separated string.
     * @param value the comma-separated string
     * @return a list of strings
     */
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

    /**
     * Creates a sorted ExecuteProcessUI from a list of process element UI.
     * @param processList the list of process element UI
     * @return a sorted ExecuteProcessUI
     */
    public ExecuteProcessUI createSortedExecuteProcessUI(List<ProcessElementUI> processList) {
        return processFactory.createExecuteProcessUI(processList);
    }
}
