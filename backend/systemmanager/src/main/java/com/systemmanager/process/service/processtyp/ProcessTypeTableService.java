package com.systemmanager.process.service.processtyp;

import com.systemmanager.database.service.domains.ProcessDefaultDBService;
import com.systemmanager.process.model.process.ExecuteProcess;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ProcessTypeTableService implements IProcessTypeService {

    private final ProcessDefaultDBService processDefaultDBService;
    private final WebClient.Builder webClient;


    public boolean executeProcess(ExecuteProcess process) {
        return switch (process.getProcessEvent()) {
            case DELETE -> deleteProcess(process);
            case RESET -> resetProcess(process);
            case RESTART -> restartProcess(process);
            default -> throw new NotFoundException("No Process Event found by Name: " + process.getProcessEvent());
        };
    }

    @Override
    public boolean deleteProcess(ExecuteProcess process) {
        processDefaultDBService.deleteAllDefaultData();
        String uri = "http://localhost:8998/restTable/delete/CLOUD_XXL/mamei_system/process";
        webClient
                .build()
                .delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class)
                .block();

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
