package com.systemmanager.database.service.domains;

import com.systemmanager.database.assets.DatabaseTableNames;
import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.process.model.process.*;
import com.systemmanager.process.model.process.EProcessTyp;
import com.systemmanager.process.model.process.Process;
import com.systemmanager.process.model.process.ProcessDefaultNameTable;
import com.systemmanager.process.repository.ProcessRepository;
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
                    tableNames));
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
                    tableNames));
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
                    tableNames));
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
        if (!processRepository.existsByProcessName(ProcessDefaultNameTable.CREATE_DEFAULT_DATASET)) {
            processRepository.save(new Process(
                    EProcessEvent.CREATE,
                    EProcessTyp.DATA_SET,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.CREATE_DEFAULT_DATASET,
                    "Create default Dataset",
                    false,
                    "/",
                    tableNames));
        }

        return addDependedProcessIdsToProcess();
    }


    public boolean addDependedProcessIdsToProcess() {
        List<Process> processList = processRepository.findAll();

        for (Process process : processList) {
            if (process.getProcessName().equals(ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET)) {
                StringBuilder sb = new StringBuilder();
                process.setHasDependedProcess(true);
                processList.stream().forEach(result -> {
                    if (result.getProcessName().equals(ProcessDefaultNameTable.DELETE_DATASET)) {
                        sb.append(result.getId().toString() + ", ");
                    } else if (result.getProcessName().equals(ProcessDefaultNameTable.CREATE_DEFAULT_DATASET)) {
                        sb.append(result.getId().toString() + ", ");
                    }
                });
                sb.delete(sb.length() - 2, sb.length());
                process.setDependedProcessIds(sb.toString());
            } else if (process.getProcessName().equals(ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET)) {
                process.setHasDependedProcess(true);
                processList.stream().forEach(result -> {
                    if (result.getProcessName().equals(ProcessDefaultNameTable.RESTART_MICROSERVICE)) {
                        process.setDependedProcessIds(result.getId().toString());
                    }
                });

            }
        }
        processRepository.saveAll(processList);
        return true;
    }

    public void sortDependedProcessIdsFromProcess() {

    }

}

