package com.apigateway.api.process.service;

import com.apigateway.api.database.assets.DatabaseTableNames;
import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import com.apigateway.api.process.model.process.EProcessTyp;
import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.process.Process;
import com.apigateway.api.process.model.process.ProcessDefaultNameTable;
import com.apigateway.api.process.model.ui.ProcessElementUI;
import org.springframework.stereotype.Service;

@Service
public class ProcessRuleService {


    /**
     * Check, if {@link ExecuteProcess} is a process or a procedure.
     *
     * @param process
     * @return
     */
    public boolean isProcedure(ExecuteProcess process) {
        return ProcessDefaultNameTable.procedureProcessNameList.stream().anyMatch(processName -> process.getProcessName().equals(processName));
    }

    /**
     * Get the right Theme for the current Process from the Procedure
     */
    public String getThemeFromProcedureForProcess(ProcessElementUI processElementUI, Process process, String theme) {
        if (isProcessIsSameAsProcedureProcessTyp(processElementUI.getProcessTyp(), process.getProcessTyp())) {
            return theme;
        }
        if(process.getProcessTyp().equals(EProcessTyp.MICRO_SERVICES)){
            if(DatabaseTableNames.apiTableNameList.contains(theme)){
                return EurekaDiscoveryClientNameTable.ApiGateWay;
            }
        }
        return null;
    }

    public boolean isProcessIsSameAsProcedureProcessTyp(EProcessTyp procedureProcessTyp, EProcessTyp processTyp) {
        if (procedureProcessTyp.equals(processTyp)) {
            return true;
        } else if (procedureProcessTyp.equals(EProcessTyp.DATA_SET) && processTyp.equals(EProcessTyp.TABLE)) {
            return true;
        }else if (procedureProcessTyp.equals(EProcessTyp.TABLE) && processTyp.equals(EProcessTyp.DATA_SET)) {
            return true;
        }
        return false;
    }

}
