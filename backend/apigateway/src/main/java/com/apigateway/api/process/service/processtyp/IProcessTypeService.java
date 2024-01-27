package com.apigateway.api.process.service.processtyp;

import com.apigateway.api.process.model.ExecuteProcess;

public interface IProcessTypeService {
    boolean deleteProcess(ExecuteProcess process);
    boolean resetProcess(ExecuteProcess process);
}
