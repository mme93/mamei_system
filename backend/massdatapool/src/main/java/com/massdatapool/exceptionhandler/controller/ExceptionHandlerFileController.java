package com.massdatapool.exceptionhandler.controller;

import com.massdatapool.exceptionhandler.service.ExceptionHandlerFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionHandlerFileController {

    private final ExceptionHandlerFileService exceptionHandlerFileService;

    @Autowired
    public ExceptionHandlerFileController(ExceptionHandlerFileService exceptionHandlerFileService) {
        this.exceptionHandlerFileService = exceptionHandlerFileService;
    }

    @GetMapping("/log_file")
    public ResponseEntity getLogFile() {
        return new ResponseEntity(HttpStatus.OK);
    }


}
