package com.apigateway.api.micro_services.systemmanager.controller;

import com.apigateway.api.micro_services.systemmanager.service.SystemManagerService;
import com.apigateway.security.service.UserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/ping")
    public ResponseEntity<String> getPing(){
        return new ResponseEntity<>(service.getPing(), HttpStatus.OK);
    }

    //MicroService
    @GetMapping("/service_status")
    public ResponseEntity<List<Object>> getMicroServicesStatus() {
        return new ResponseEntity(this.service.getMicroServicesStatus(), HttpStatus.OK);
    }

    @GetMapping("/service_status/{microServiceName}")
    public ResponseEntity<Object> getMicroServiceStatus(@PathVariable String microServiceName) {
        try {
            return new ResponseEntity<>(this.service.getMicroServiceStatus(microServiceName), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Process
    @PostMapping("/process/newJob")
    public ResponseEntity<Boolean> startNewJob(@RequestBody Object process){
        return new ResponseEntity<>(this.service.startProcess(process),HttpStatus.OK);
    }


    @GetMapping("/process")
    public ResponseEntity<List<Object>>getProcessList(){
        return new ResponseEntity<>(this.service.getProcessElementUI(),HttpStatus.OK);
    }

    @PutMapping("/process/sort")
    public ResponseEntity<Object>createSortedExecuteProcessUI(@RequestBody List<Object> processList){
        return new ResponseEntity(this.service.createSortedExecuteProcessUI(processList),HttpStatus.OK);
    }

    @PostMapping("/process/set_default")
    public ResponseEntity setDefault(){
        this.service.setDefault();
        return new ResponseEntity(HttpStatus.OK);
    }

    //ProcessProtocolController
    @PostMapping("/process/protocol/create/{task_signature}")
    public ResponseEntity createTask(@PathVariable String task_signature) {
        this.service.createTaskProtocol(task_signature, userService.getCurrentUsername());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/process/protocol/close/{task_signature}")
    public ResponseEntity closeTask(@PathVariable String task_signature) {
        this.service.closeTaskProtocol(task_signature);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/process/protocol")
    public ResponseEntity<List<Object>> getTaskProcessProtocols() {
        return new ResponseEntity(this.service.getTaskProcessProtocols(), HttpStatus.OK);
    }

    @GetMapping("/process/protocol/load/{task_signature}")
    public ResponseEntity<Object> loadProtocols(@PathVariable String task_signature) {
        return new ResponseEntity(this.service.getTaskProcessProtocol(task_signature), HttpStatus.OK);
    }

    @PostMapping("/process/protocol/comment/update")
    public ResponseEntity updateUserComment(@RequestBody Object userComment){
        this.service.updateUserComment(userComment);
        return new ResponseEntity(HttpStatus.OK);
    }
}

