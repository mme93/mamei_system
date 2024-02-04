package com.apigateway.api.process.service;

import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.process.Process;
import com.apigateway.api.process.model.process.ProcessDefaultNameTable;
import org.springframework.stereotype.Service;

@Service
public class ProcessRuleService {


    /**
     * Check, if {@link ExecuteProcess} is a process or a procedure.
     *
     * @param process
     * @return
     */
    public boolean isProcedure(ExecuteProcess process){
        return ProcessDefaultNameTable.processNameList.contains(process);
    }

    /**
     * Get the right Theme for the current Process from the Procedure
     */
    public String getThemeFromProcedureForProcess(String procedureName, Process process){
        System.err.println(procedureName);
        return null;
    }

}
