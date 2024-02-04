package com.apigateway.api.process.controller;

import com.apigateway.api.process.model.protocol.TaskProcessProtocol;
import com.apigateway.api.process.model.protocol.ui.ProtocolResultUI;
import com.apigateway.api.process.service.protocol.TaskProcessProtocolService;
import com.apigateway.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protocol")
public class ProcessProtocolController {

    private final TaskProcessProtocolService taskProcessProtocolService;
    private final UserService userService;

    @Autowired
    public ProcessProtocolController(TaskProcessProtocolService taskProcessProtocolService, UserService userService) {
        this.taskProcessProtocolService = taskProcessProtocolService;
        this.userService = userService;
    }

    @PostMapping("/create/{task_signature}")
    public ResponseEntity createTask(@PathVariable String task_signature) {
        taskProcessProtocolService.createTaskProtocol(task_signature, userService.getCurrentUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/close/{task_signature}")
    public ResponseEntity closeTask(@PathVariable String task_signature) {
        taskProcessProtocolService.closeTaskProtocol(task_signature);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskProcessProtocol>> getTaskProcessProtocols() {
        return new ResponseEntity(taskProcessProtocolService.getTaskProcessProtocols(), HttpStatus.OK);
    }

    @GetMapping("/x/{task_signature}")
    public ResponseEntity<ProtocolResultUI> getX(@PathVariable String task_signature) {
        return new ResponseEntity(taskProcessProtocolService.getX(task_signature), HttpStatus.OK);
    }

    @GetMapping("/{task_signature}")
    public ResponseEntity<TaskProcessProtocol> getTaskProcessProtocol(@PathVariable String task_signature) {
        return new ResponseEntity(taskProcessProtocolService.getTaskProcessProtocol(task_signature), HttpStatus.OK);
    }
}
