package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.model.process.EProcessTyp;
import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.protocol.EProcessStatus;
import com.apigateway.api.process.model.protocol.EProcessTypProtocol;
import com.apigateway.api.process.model.protocol.ProcessProtocol;
import com.apigateway.api.process.model.protocol.TaskProcessProtocol;
import com.apigateway.api.process.repository.TaskProcessProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProcessProtocolService {

    private final TaskProcessProtocolRepository taskProcessProtocolRepository;

    @Autowired
    public ProcessProtocolService(TaskProcessProtocolRepository taskProcessProtocolRepository) {
        this.taskProcessProtocolRepository = taskProcessProtocolRepository;
    }

    public boolean createProcessProtocol(boolean isSuccessful, ExecuteProcess process, LocalDateTime start, LocalDateTime end) {
        Optional<TaskProcessProtocol> taskProcessProtocolOpt = taskProcessProtocolRepository.findBySignature(process.getTaskSignature());
        if (!taskProcessProtocolOpt.isPresent()) {
            return false;
        }
        TaskProcessProtocol taskProcessProtocol = taskProcessProtocolOpt.get();

        EProcessTypProtocol eProcessTypProtocol=null;
        EProcessStatus eProcessStatus=null;

        if(process.getSignature().contains("main")){
            eProcessTypProtocol = EProcessTypProtocol.MAIN;
        }else if(process.getSignature().contains("sub")){
            eProcessTypProtocol = EProcessTypProtocol.SUB;
        }

        if(isSuccessful){
            eProcessStatus = EProcessStatus.PASSED;
        }else {
            eProcessStatus = EProcessStatus.FAILED;
        }

        ProcessProtocol processProtocol = ProcessProtocol.builder()
                .signature(process.getSignature())
                .parent_signature(process.getTaskSignature())
                .processName(process.getProcessName())
                .processText(process.getProcessText()+" "+process.getTheme())
                .executeProcessDate(start.toString())
                .executeEndProcessDate(end.toString())
                .eProcessTypProtocol(eProcessTypProtocol)
                .eProcessStatus(eProcessStatus)
                .result(generateResult(isSuccessful,process))
                .build();

        List<ProcessProtocol> resultProcessProtocols = taskProcessProtocol.getProcessProtocols();
        resultProcessProtocols.add(processProtocol);
        taskProcessProtocol.setProcessProtocols(resultProcessProtocols);
        taskProcessProtocolRepository.save(taskProcessProtocol);
        return true;
    }

    public String generateResult(boolean isSuccessful,ExecuteProcess process){
        return "Result";
    }

}