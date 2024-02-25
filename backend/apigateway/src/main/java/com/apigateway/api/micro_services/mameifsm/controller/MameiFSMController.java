package com.apigateway.api.micro_services.mameifsm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling Mamei FSM (Frontend Service Manager) related endpoints.
 */
@RestController
@RequestMapping("mameifsm")
public class MameiFSMController {

    /**
     * Endpoint to check the health status of the Mamei FSM.
     * @return ResponseEntity with "Ping" message and HTTP status OK (200).
     */
    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }
}
