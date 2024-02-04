package com.apigateway.api.process.service.factory;

import com.apigateway.api.process.model.process.Process;
import com.apigateway.api.process.model.ui.ExecuteMainProcess;
import com.apigateway.api.process.model.ui.ExecuteProcessUI;
import com.apigateway.api.process.model.ui.ExecuteSubProcess;
import com.apigateway.api.process.model.ui.ProcessElementUI;
import com.apigateway.api.process.repository.ProcessRepository;
import com.apigateway.api.process.service.ProcessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ExecuteProcessFactory {

    private final ProcessRepository processRepository;
    private final ProcessRuleService processRuleService;

    @Autowired
    public ExecuteProcessFactory(ProcessRepository processRepository, ProcessRuleService processRuleService) {
        this.processRepository = processRepository;
        this.processRuleService = processRuleService;
    }

    public ExecuteProcessUI createExecuteProcessUI(List<ProcessElementUI> processList) {
        List<ExecuteMainProcess> executeMainProcesses = createExecuteMainProcess(processList);
        return ExecuteProcessUI.
                builder().
                signature(createSignature("task_")).
                executeMainProcesses(executeMainProcesses).
                processDuration("/").
                mainProcessAmount(executeMainProcesses.size()).
                build();
    }

    public List<ExecuteMainProcess> createExecuteMainProcess(List<ProcessElementUI> processList) {
        List<ExecuteMainProcess> executeMainProcesses = new ArrayList<>();
        for (ProcessElementUI elementUI : processList) {
            if (elementUI.getScopeList().size() > 0) {
                executeMainProcesses.addAll(createExecuteMainProcessIfScopeExist(elementUI));
            }
        }
        return executeMainProcesses;
    }

    public List<ExecuteMainProcess> createExecuteMainProcessIfScopeExist(ProcessElementUI elementUI) {
        List<ExecuteMainProcess> executeMainProcesses = new ArrayList<>();
        for (String scope : elementUI.getScopeList()) {
            executeMainProcesses.add(new ExecuteMainProcess(
                    createSignature("main_"),
                    scope,
                    elementUI.getProcessEvent(),
                    elementUI.getProcessTyp(),
                    elementUI.getProcessClassification(),
                    elementUI.getProcessPlausibility(),
                    elementUI.getProcessName(),
                    elementUI.getProcessText(),
                    elementUI.getDependedProcessIds().size(),
                    createExecuteSubProcess(elementUI,scope),
                    ""
            ));
        }

        return executeMainProcesses;
    }

    public List<ExecuteSubProcess> createExecuteSubProcess(ProcessElementUI elementUI, String theme) {
        List<ExecuteSubProcess> executeSubProcesses = new ArrayList<>();
        if (elementUI.getDependedProcessIds().size() == 0) {
            return executeSubProcesses;
        }
        for (String id : elementUI.getDependedProcessIds()) {
            Optional<Process> processOpt = processRepository.findById(Long.valueOf(id));
            if (processOpt.isPresent()) {
                Process process = processOpt.get();
                executeSubProcesses.add(new ExecuteSubProcess(
                        createSignature("sub_"),
                        processRuleService.getThemeFromProcedureForProcess(elementUI,process,theme),
                        process.getProcessEvent(),
                        process.getProcessTyp(),
                        process.getProcessClassification(),
                        process.getProcessPlausibility(),
                        process.getProcessName(),
                        process.getProcessText(),
                        ""
                ));
            }
        }
        return executeSubProcesses;
    }

    public String createSignature(String type) {
        String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomStringBuilder = new StringBuilder(10);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            randomStringBuilder.append(randomChar);
        }

        return type + randomStringBuilder.toString();
    }

}
