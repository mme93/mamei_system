package com.apigateway.api.process.controller;

import com.apigateway.api.process.service.protocol.TaskProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protocol")
public class ProcessProtocolController {

    private final TaskProtocolService taskProtocolService;

    @Autowired
    public ProcessProtocolController(TaskProtocolService taskProtocolService) {
        this.taskProtocolService = taskProtocolService;
    }

    @PostMapping("/{task_signature}")
    public ResponseEntity createTask(@PathVariable String task_signature){
        taskProtocolService.createTaskProtocol(task_signature);
        return new ResponseEntity(HttpStatus.OK);
    }

}
