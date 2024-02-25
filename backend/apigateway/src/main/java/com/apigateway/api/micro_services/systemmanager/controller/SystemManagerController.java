package com.apigateway.api.micro_services.systemmanager.controller;

import com.apigateway.api.micro_services.systemmanager.service.SystemManagerService;
import com.apigateway.security.service.UserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling system management related HTTP requests.
 */
@RestController
@RequestMapping("/system")
public class SystemManagerController {

    private final SystemManagerService service;
    private final UserService userService;

    @Autowired
    public SystemManagerController(SystemManagerService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    /**
     * Retrieves a ping response from the system manager service.
     * @return A ResponseEntity containing the ping response and HTTP status.
     */
    @GetMapping("/ping")
    public ResponseEntity<String> getPing(){
        return new ResponseEntity<>(service.getPing(), HttpStatus.OK);
    }

    /******************************MicroService******************************/


    /**
     * Retrieves the status of all microservices.
     * @return A ResponseEntity containing a list of microservice statuses and HTTP status.
     */
    @GetMapping("/service_status")
    public ResponseEntity<List<Object>> getMicroServicesStatus() {
        return new ResponseEntity(this.service.getMicroServicesStatus(), HttpStatus.OK);
    }

    /**
     * Retrieves the status of a specific microservice.
     * @param microServiceName The name of the microservice.
     * @return A ResponseEntity containing the microservice status and HTTP status.
     */
    @GetMapping("/service_status/{microServiceName}")
    public ResponseEntity<Object> getMicroServiceStatus(@PathVariable String microServiceName) {
        try {
            return new ResponseEntity<>(this.service.getMicroServiceStatus(microServiceName), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /******************************Process******************************/

    /**
     * Starts a new job process.
     * @param process The process to start.
     * @return A ResponseEntity indicating the success of starting the process and HTTP status.
     */
    @PostMapping("/process/newJob")
    public ResponseEntity<Boolean> startNewJob(@RequestBody Object process){
        return new ResponseEntity<>(this.service.startProcess(process),HttpStatus.OK);
    }

    /**
     * Retrieves a list of processes.
     * @return A ResponseEntity containing a list of processes and HTTP status.
     */
    @GetMapping("/process")
    public ResponseEntity<List<Object>>getProcessList(){
        return new ResponseEntity<>(this.service.getProcessElementUI(),HttpStatus.OK);
    }

    /**
     * Creates a sorted list of execute process UI.
     * @param processList The list of processes to sort.
     * @return A ResponseEntity containing the sorted list of processes and HTTP status.
     */
    @PutMapping("/process/sort")
    public ResponseEntity<Object>createSortedExecuteProcessUI(@RequestBody List<Object> processList){
        return new ResponseEntity(this.service.createSortedExecuteProcessUI(processList),HttpStatus.OK);
    }

    /**
     * Sets the default process.
     * @return A ResponseEntity indicating the success of setting the default process and HTTP status.
     */
    @PostMapping("/process/set_default")
    public ResponseEntity setDefault(){
        this.service.setDefault();
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Creates a task protocol.
     * @param task_signature The signature of the task.
     * @return A ResponseEntity indicating the success of creating the task protocol and HTTP status.
     */
    @PostMapping("/process/protocol/create/{task_signature}")
    public ResponseEntity createTask(@PathVariable String task_signature) {
        this.service.createTaskProtocol(task_signature, userService.getCurrentUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Closes a task protocol.
     * @param task_signature The signature of the task.
     * @return A ResponseEntity indicating the success of closing the task protocol and HTTP status.
     */
    @PostMapping("/process/protocol/close/{task_signature}")
    public ResponseEntity closeTask(@PathVariable String task_signature) {
        this.service.closeTaskProtocol(task_signature);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Retrieves task process protocols.
     * @return A ResponseEntity containing a list of task process protocols and HTTP status.
     */
    @GetMapping("/process/protocol")
    public ResponseEntity<List<Object>> getTaskProcessProtocols() {
        return new ResponseEntity(this.service.getTaskProcessProtocols(), HttpStatus.OK);
    }

    /**
     * Loads task process protocols.
     * @param task_signature The signature of the task.
     * @return A ResponseEntity containing the task process protocol and HTTP status.
     */
    @GetMapping("/process/protocol/load/{task_signature}")
    public ResponseEntity<Object> loadProtocols(@PathVariable String task_signature) {
        return new ResponseEntity(this.service.getTaskProcessProtocol(task_signature), HttpStatus.OK);
    }

    /**
     * Updates user comment in task process protocol.
     * @param userComment The user comment to update.
     * @return A ResponseEntity indicating the success of updating the user comment and HTTP status.
     */
    @PostMapping("/process/protocol/comment/update")
    public ResponseEntity updateUserComment(@RequestBody Object userComment){
        this.service.updateUserComment(userComment);
        return new ResponseEntity(HttpStatus.OK);
    }
}

