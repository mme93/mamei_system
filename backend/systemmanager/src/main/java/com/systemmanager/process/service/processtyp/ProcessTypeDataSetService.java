package com.systemmanager.process.service.processtyp;

import com.systemmanager.database.assets.DatabaseTableNames;
import com.systemmanager.database.service.domains.MicroServiceDomainDefaultDBService;
import com.systemmanager.database.service.domains.ProcessDefaultDBService;
import com.systemmanager.database.service.system.SecurityUserDefaultDBService;
import com.systemmanager.process.model.process.ExecuteProcess;
import com.systemmanager.process.model.process.ProcessDefaultNameTable;
import com.systemmanager.process.repository.ProcessRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for executing dataset-related processes.
 */
@Service
public class ProcessTypeDataSetService implements IProcessTypeService {

    private final ProcessRepository processRepository;
    private final ProcessDefaultDBService processDefaultDBService;
    private final SecurityUserDefaultDBService securityUserDefaultDBService;
    private final MicroServiceDomainDefaultDBService microServiceDomainDefaultDBService;

    @Autowired
    public ProcessTypeDataSetService(ProcessRepository processRepository, ProcessDefaultDBService processDefaultDBService, SecurityUserDefaultDBService securityUserDefaultDBService, MicroServiceDomainDefaultDBService microServiceDomainDefaultDBService) {
        this.processRepository = processRepository;
        this.processDefaultDBService = processDefaultDBService;
        this.securityUserDefaultDBService = securityUserDefaultDBService;
        this.microServiceDomainDefaultDBService = microServiceDomainDefaultDBService;
    }

    /**
     * Executes a dataset-related process.
     * @param process the process to execute
     * @return true if the process executed successfully, otherwise false
     */
    public boolean executeProcess(ExecuteProcess process) {
        return switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            case CREATE -> createProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
    }

    /**
     *
     * @param process the process to execute
     * @return true if the process is created, otherwise false
     */
    private boolean createProcess(ExecuteProcess process) {
        if (process.getProcessName().equals(ProcessDefaultNameTable.CREATE_DEFAULT_DATASET)) {
            if (process.getTheme().equals(DatabaseTableNames.PROCESS)) {
                return processDefaultDBService.loadDefaultDataIntoDatabase();
            }else if (process.getTheme().equals(DatabaseTableNames.MICRO_SERVICE)) {
                microServiceDomainDefaultDBService.loadDefaultDataIntoDatabase();
            }
        }
        return true;
    }

    /**
     * Deletes dataset-related process.
     * @param process the process to delete
     * @return true if the process deleted successfully, otherwise false
     */
    @Override
    public boolean deleteProcess(ExecuteProcess process) {
        if (process.getProcessName().equals(ProcessDefaultNameTable.DELETE_DATASET)) {
            if (process.getTheme().equals(DatabaseTableNames.PROCESS)) {
                processDefaultDBService.deleteAllData();
            }else if (process.getTheme().equals(DatabaseTableNames.MICRO_SERVICE)) {
                microServiceDomainDefaultDBService.deleteAllData();
            }
        }
        return true;
    }

    /**
     * Resets dataset-related process.
     * @param process the process to reset
     * @return true if the process reset successfully, otherwise false
     */
    @Override
    public boolean resetProcess(ExecuteProcess process) {

        if(process.getProcessName().equals(ProcessDefaultNameTable.RESET_SECURITY_USER_TO_DEFAULT_DATASET)){
           return securityUserDefaultDBService.reloadDefaultDataIntoDatabase();
        }

        return false;
    }

    /**
     * Restarts dataset-related process.
     * @param process the process to restart
     * @return always returns false as restarting process is not implemented
     */
    @Override
    public boolean restartProcess(ExecuteProcess process) {
        return false;
    }
}
