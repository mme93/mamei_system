package com.apigateway.api.micro_services.configmanager.controller;

import com.apigateway.api.micro_services.configmanager.service.ConfigManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configmanager")
public class ConfigManagerController {

    private final ConfigManagerService configManagerService;

    public ConfigManagerController(ConfigManagerService configManagerService) {
        this.configManagerService = configManagerService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getPing(){
        return new ResponseEntity<>( configManagerService.ping(), HttpStatus.OK);
    }

}
