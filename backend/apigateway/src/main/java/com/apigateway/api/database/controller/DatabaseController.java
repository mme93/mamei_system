package com.apigateway.api.database.controller;

import com.apigateway.api.database.service.DatabaseInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseInitService initService;

    @Autowired
    public DatabaseController(DatabaseInitService initService) {
        this.initService = initService;
    }

    @PostMapping("/init")
    public void initDatabase(){
        initService.initDatabase();
    }

}
