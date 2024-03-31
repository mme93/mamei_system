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

/**
 * Controller class for managing process protocols.
 */
@RestController
@RequestMapping("/protocol")
public class ProcessProtocolController {

    private final TaskProcessProtocolService taskProcessProtocolService;


    /**
     * Constructor for ProcessProtocolController.
     *
     * @param taskProcessProtocolService The TaskProcessProtocolService instance.
     */
    @Autowired
    public ProcessProtocolController(TaskProcessProtocolService taskProcessProtocolService
    ) {
        this.taskProcessProtocolService = taskProcessProtocolService;
    }

    /**
     * Endpoint to create a task protocol.
     *
     * @param task_signature  The signature of the task.
     * @param currentUsername The username of the current user.
     * @return ResponseEntity with HTTP status OK.
     */
    @PostMapping("/create/{task_signature}/{currentUsername}")
    public ResponseEntity createTask(@PathVariable String task_signature, @PathVariable String currentUsername) {
        taskProcessProtocolService.createTaskProtocol(task_signature, currentUsername);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to close a task protocol.
     *
     * @param task_signature The signature of the task.
     * @return ResponseEntity with HTTP status OK.
     */
    @PostMapping("/close/{task_signature}")
    public ResponseEntity closeTask(@PathVariable String task_signature) {
        taskProcessProtocolService.closeTaskProtocol(task_signature);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve all task process protocols.
     *
     * @return ResponseEntity with a list of TaskProcessProtocol.
     */
    @GetMapping("/")
    public ResponseEntity<List<TaskProcessProtocol>> getTaskProcessProtocols() {
        return new ResponseEntity(taskProcessProtocolService.getTaskProcessProtocols(), HttpStatus.OK);
    }

    /**
     * Endpoint to load protocols for a specific task.
     *
     * @param task_signature The signature of the task.
     * @return ResponseEntity with a ProtocolResultUI object.
     */
    @GetMapping("/load/{task_signature}")
    public ResponseEntity<ProtocolResultUI> loadProtocols(@PathVariable String task_signature) {
        return new ResponseEntity(taskProcessProtocolService.getTaskProcessProtocol(task_signature), HttpStatus.OK);
    }


    /**
     * Endpoint to update user comment for a protocol.
     *
     * @param userComment The ProtocolUserComment object containing updated comment.
     * @return ResponseEntity with HTTP status OK.
     */
    @PostMapping("/comment/update")
    public ResponseEntity updateUserComment(@RequestBody ProtocolUserComment userComment) {
        taskProcessProtocolService.updateUserComment(userComment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
