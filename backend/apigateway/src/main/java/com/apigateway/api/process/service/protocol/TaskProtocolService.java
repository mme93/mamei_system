package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.repository.TaskProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskProtocolService {

    private final TaskProtocolRepository taskProtocolRepository;

    @Autowired
    public TaskProtocolService(TaskProtocolRepository taskProtocolRepository) {
        this.taskProtocolRepository = taskProtocolRepository;
    }

    public void createTaskProtocol(){

    }

}
