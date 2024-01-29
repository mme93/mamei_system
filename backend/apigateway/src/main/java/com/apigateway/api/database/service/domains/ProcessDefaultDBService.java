package com.apigateway.api.database.service.domains;

import com.apigateway.api.database.assets.DatabaseTableNames;
import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import com.apigateway.api.process.model.*;
import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessDefaultDBService implements IDefaultDBService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessDefaultDBService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public boolean updateProcessDependentArray() {

        return true;
    }

    @Override
    public boolean deleteAllData() {
        this.processRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteAllDefaultData() {
        this.processRepository.findAll().stream().forEach(process -> {
            if (ProcessDefaultNameTable.processNameList.contains(process.getProcessName())) {
                this.processRepository.deleteById(process.getId());
            }
        });
        return true;
    }

    @Override
    public boolean existTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean loadDefaultDataIntoDatabase() {
        String eurekaDiscoverClientNames = EurekaDiscoveryClientNameTable.eurekaDiscoverClientNames;
        String tableNames = DatabaseTableNames.tableNames;

        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_DATASET)) {
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.DATA_SET,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_DATASET,
                    "Remove all data from Table",
                    false,
                    "/",
                    tableNames));
        }
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_DEFAULT_DATASET)) {
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.DATA_SET,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_DEFAULT_DATASET,
                    "Remove default data from Table",
                    false,
                    "/",
                    "/"));
        }
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET)) {
            processRepository.save(new Process(
                    EProcessEvent.RESET,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET,
                    "Reset Table data to default",
                    false,
                    "/",
                    "/"));
        }
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_TABLE)) {
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_TABLE,
                    "Delete table",
                    false,
                    "/",
                    "/"));
        }
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET)) {
            processRepository.save(new Process(
                    EProcessEvent.RESET,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET,
                    "Reset all Table data to default",
                    false,
                    "/",
                    "/"));
        }
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.RESTART_MICROSERVICE)) {
            processRepository.save(new Process(
                    EProcessEvent.RESTART,
                    EProcessTyp.MICRO_SERVICES,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESTART_MICROSERVICE,
                    "Restart Microservice",
                    false,
                    "/",
                    eurekaDiscoverClientNames));
        }

        return addDependedProcessIdsToProcess();
    }


    public boolean addDependedProcessIdsToProcess() {
        List<Process> processList = processRepository.findAll();

        for (Process process : processList) {
            if (process.getProcessName().equals(ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET)) {
                process.setHasDependedProcess(true);
                processList.stream().forEach(result-> {
                    if(result.getProcessName().equals(ProcessDefaultNameTable.RESTART_MICROSERVICE)){
                        process.setDependedProcessIds(result.getId().toString());
                    }
                });
            } else if (process.getProcessName().equals(ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET)) {
                process.setHasDependedProcess(true);
                processList.stream().forEach(result-> {
                    if(result.getProcessName().equals(ProcessDefaultNameTable.RESTART_MICROSERVICE)){
                        process.setDependedProcessIds(result.getId().toString());
                    }
                });

            }
        }
        processRepository.saveAll(processList);
        return true;
    }
}

