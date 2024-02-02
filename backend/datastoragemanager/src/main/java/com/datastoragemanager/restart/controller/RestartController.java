package com.datastoragemanager.restart.controller;

import com.datastoragemanager.restart.service.RestartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restart")
public class RestartController {

    private final RestartService restartService;

    @Autowired
    public RestartController(RestartService restartService) {
        this.restartService = restartService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> restart(){
        return new ResponseEntity<>(restartService.restart(),HttpStatus.OK);
    }

}
