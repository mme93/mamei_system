package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.database.assets.DatabaseTableNames;
import com.apigateway.api.database.service.domains.ProcessDefaultDBService;
import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.process.ProcessDefaultNameTable;
import com.apigateway.api.process.repository.ProcessRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTypeDataSetService implements IProcessTypeService {

    private final ProcessRepository processRepository;
    private final ProcessDefaultDBService processDefaultDBService;

    @Autowired
    public ProcessTypeDataSetService(ProcessRepository processRepository, ProcessDefaultDBService processDefaultDBService) {
        this.processRepository = processRepository;
        this.processDefaultDBService = processDefaultDBService;
    }

    public boolean executeProcess(ExecuteProcess process) {
        return switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            case CREATE -> createProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
    }

    private boolean createProcess(ExecuteProcess process) {
        if (process.getProcessName().equals(ProcessDefaultNameTable.CREATE_DEFAULT_DATASET)) {
            if (process.getTheme().equals(DatabaseTableNames.PROCESS)) {
                return processDefaultDBService.loadDefaultDataIntoDatabase();
            }
        }
        return false;
    }

    @Override
    public boolean deleteProcess(ExecuteProcess process) {
        if (process.getProcessName().equals(ProcessDefaultNameTable.DELETE_DATASET)) {
            if (process.getTheme().equals(DatabaseTableNames.PROCESS)) {
                processRepository.deleteAll();
            }
        }
        return true;
    }

    @Override
    public boolean resetProcess(ExecuteProcess process) {
        return false;
    }

    @Override
    public boolean restartProcess(ExecuteProcess process) {
        return false;
    }
}
