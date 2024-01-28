package com.apigateway.api.database.service.domains;

import com.apigateway.api.process.model.*;
import com.apigateway.api.process.model.Process;
import com.apigateway.api.process.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessDefaultDBService implements IDefaultDBService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessDefaultDBService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public boolean updateProcessDependentArray(){

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
            if(ProcessDefaultNameTable.processNameList.contains(process.getProcessName())){
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

    public boolean test(){
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_DATASET)){
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.DATA_SET,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_DATASET,
                    "Remove all data from Table",
                    false,
                    false,
                    "[]"));
        }
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_DEFAULT_DATASET)){
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.DATA_SET,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_DEFAULT_DATASET,
                    "Remove default data from Table",
                    false,
                    false,
                    "[]"));
        }
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET)){
            processRepository.save(new Process(
                    EProcessEvent.RESET,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESET_TO_DEFAULT_DATASET,
                    "Reset Table data to default",
                    false,
                    false,
                    "[]"));
        }
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.DELETE_TABLE)){
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.DELETE_TABLE,
                    "Delete table",
                    false,
                    false,
                    "[]"));
        }
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET)){
            processRepository.save(new Process(
                    EProcessEvent.RESET,
                    EProcessTyp.TABLE,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESET_ALL_TO_DEFAULT_DATASET,
                    "Reset all Table data to default",
                    false,
                    false,
                    "[]"));
        }
        if(!processRepository.existsByProcessName(ProcessDefaultNameTable.RESTART_MICROSERVICE)){
            processRepository.save(new Process(
                    EProcessEvent.RESTART,
                    EProcessTyp.MICRO_SERVICES,
                    EProcessClassification.LOW,
                    EProcessPlausibility.NONE,
                    ProcessDefaultNameTable.RESTART_MICROSERVICE,
                    "Restart Microservice",
                    false,
                    false,
                    "[]"));
        }


        return true;
    }

    @Override
    public boolean loadDefaultDataIntoDatabase() {
        /*
        if(!processRepository.existsByProcessName("BACKUP_DATABASE")){
            processRepository.save(new Process(
                    EProcessEvent.BUILD,
                    EProcessTyp.DATABASE,
                    "BACKUP_DATABASE",
                    "BackUp Database mamei_system",
                    false,
                    false,
                    "[]"));
        }if(!processRepository.existsByProcessName("CREATE_DATABASE")){
            processRepository.save(new Process(
                    EProcessEvent.CREATE,
                    EProcessTyp.DATABASE,
                    "CREATE_DATABASE",
                    "Create Database mamei_system",
                    false,
                    false,
                    "[]"));
        } if(!processRepository.existsByProcessName("DELETE_DATABASE")){
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.DATABASE,
                    "DELETE_DATABASE",
                    "Delete Database mamei_system",
                    false,
                    false,
                    "[]"));
        }if(!processRepository.existsByProcessName("CREATE_PROCESS_SET")){
            processRepository.save(new Process(
                    EProcessEvent.CREATE,
                    EProcessTyp.DATABASE,
                    "CREATE_PROCESS_SET",
                    "Create Process set",
                    false,
                    false,
                    "[]"));
        }if(!processRepository.existsByProcessName("UPDATE_PROCESS_DEPENDED_ARRAY")){
            processRepository.save(new Process(
                    EProcessEvent.CREATE,
                    EProcessTyp.DATABASE,
                    "UPDATE_PROCESS_DEPENDED_ARRAY",
                    "Update Array with Dependent Processes ID",
                    false,
                    false,
                    "[]"));
        } if(!processRepository.existsByProcessName("CLEAR_TABLE")){
            processRepository.save(new Process(
                    EProcessEvent.DELETE,
                    EProcessTyp.TABLE,
                    "CLEAR_TABLE",
                    "Remove all data from Table",
                    false,
                    false,
                    "[]"));
        } if(!processRepository.existsByProcessName("SET_DEFAULT_TABLE")){
            processRepository.save(new Process(
                    EProcessEvent.UPDATE,
                    EProcessTyp.TABLE,
                    "SET_DEFAULT_TABLE",
                    "Set default table data",
                    false,
                    false,
                    "[]"));
        } if(!processRepository.existsByProcessName("RESTART_ALL_MICROSERVICES")){
            processRepository.save(new Process(
                    EProcessEvent.RESTART,
                    EProcessTyp.MICRO_SERVICES,
                    "RESTART_ALL_MICROSERVICES",
                    "Restart all Microservices",
                    false,
                    false,
                    "[]"));
        }  if(!processRepository.existsByProcessName("RESTART_MICROSERVICES")){
            processRepository.save(new Process(
                    EProcessEvent.CREATE,
                    EProcessTyp.MICRO_SERVICES,
                    "RESTART_MICROSERVICES",
                    "Restart amount of Microservices",
                    false,
                    false,
                    "[]"));
        }
        for(String microServices: EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList){
            if(!processRepository.existsByProcessName("RESTART_MICROSERVICES_"+microServices)){
                processRepository.save(new Process(
                        EProcessEvent.RESTART,
                        EProcessTyp.MICRO_SERVICES,
                        "RESTART_MICROSERVICES",
                        "Restart "+microServices+" Microservice",
                        false,
                        false,
                        "[]"));
            }
            if(!processRepository.existsByProcessName("STOP_MICROSERVICES_"+microServices)){
                processRepository.save(new Process(
                        EProcessEvent.RESTART,
                        EProcessTyp.MICRO_SERVICES,
                        "STOP_MICROSERVICES",
                        "Stop "+microServices+" Microservice",
                        false,
                        false,
                        "[]"));
            }
            if(!processRepository.existsByProcessName("START_MICROSERVICES")){
                processRepository.save(new Process(
                        EProcessEvent.RESTART,
                        EProcessTyp.MICRO_SERVICES,
                        "START_MICROSERVICES_"+microServices,
                        "Start "+microServices+" Microservice",
                        false,
                        false,
                        "[]"));
            }
        }
         */
        return false;
    }
}

