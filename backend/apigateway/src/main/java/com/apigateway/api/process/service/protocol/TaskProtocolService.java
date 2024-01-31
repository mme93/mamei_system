package com.apigateway.api.process.service.protocol;

import com.apigateway.api.process.model.protocol.ETaskStatus;
import com.apigateway.api.process.model.protocol.TaskProtocol;
import com.apigateway.api.process.repository.TaskProtocolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Arrays.asList;


@Service
public class TaskProtocolService {

    private final TaskProtocolRepository taskProtocolRepository;

    @Autowired
    public TaskProtocolService(TaskProtocolRepository taskProtocolRepository) {
        this.taskProtocolRepository = taskProtocolRepository;
    }

    public void createTaskProtocol(String task_signature) {
        taskProtocolRepository.save(new TaskProtocol(
                new Date(),
                null,
                task_signature,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                asList()
        ));

    }

}
