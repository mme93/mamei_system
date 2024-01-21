package com.apigateway.api.micro_services.mameifsm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mameifsm")
public class MameiFSMController {

    @GetMapping("/ping")
    public ResponseEntity<String>getPing(){
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }
}
