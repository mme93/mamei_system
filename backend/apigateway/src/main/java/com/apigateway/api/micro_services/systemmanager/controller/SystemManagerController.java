package com.apigateway.api.micro_services.systemmanager.controller;

import com.apigateway.api.micro_services.systemmanager.service.SystemManagerService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemManagerController {

    private final SystemManagerService service;

    @Autowired
    public SystemManagerController(SystemManagerService service) {
        this.service = service;
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

}

