package com.systemmanager.process.service.processtyp;

import com.systemmanager.process.model.process.ExecuteProcess;

public interface IProcessTypeService {
    boolean deleteProcess(ExecuteProcess process);
    boolean resetProcess(ExecuteProcess process);
    boolean restartProcess(ExecuteProcess process);
}
