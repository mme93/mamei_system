package com.systemmanager.process.controller;

import com.systemmanager.process.model.protocol.ProtocolUserComment;
import com.systemmanager.process.model.protocol.TaskProcessProtocol;
import com.systemmanager.process.model.protocol.ui.ProtocolResultUI;
import com.systemmanager.process.service.protocol.TaskProcessProtocolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/protocol")
public class ProcessProtocolController {

    private final TaskProcessProtocolService taskProcessProtocolService;

    @Autowired
    public ProcessProtocolController(TaskProcessProtocolService taskProcessProtocolService
    ) {
        this.taskProcessProtocolService = taskProcessProtocolService;
    }

    @PostMapping("/create/{task_signature}/{currentUsername}")
    public ResponseEntity createTask(@PathVariable String task_signature, @PathVariable String currentUsername) {
        taskProcessProtocolService.createTaskProtocol(task_signature, currentUsername);
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

    @GetMapping("/load/{task_signature}")
    public ResponseEntity<ProtocolResultUI> loadProtocols(@PathVariable String task_signature) {
        return new ResponseEntity(taskProcessProtocolService.getTaskProcessProtocol(task_signature), HttpStatus.OK);
    }

    @PostMapping("/comment/update")
    public ResponseEntity updateUserComment(@RequestBody ProtocolUserComment userComment){
        taskProcessProtocolService.updateUserComment(userComment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
