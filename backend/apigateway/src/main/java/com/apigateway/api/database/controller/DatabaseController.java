package com.apigateway.api.database.controller;

import com.apigateway.api.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService initService;

    @Autowired
    public DatabaseController(DatabaseService initService) {
        this.initService = initService;
    }

    @PostMapping("/init")
    public void initDatabase() {
        initService.initDatabase();
    }

    @PostMapping("/rebuild")
    public void rebuildDatabase() {
        initService.rebuildDatabase();
    }

}
