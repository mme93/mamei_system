package com.apigateway.api.services.systemmanager.controller;

import com.apigateway.api.services.systemmanager.service.SystemManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
