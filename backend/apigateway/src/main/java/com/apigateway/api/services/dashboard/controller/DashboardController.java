package com.apigateway.api.services.dashboard.controller;

import com.apigateway.api.services.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTask(@RequestBody Object task){
        return  new ResponseEntity<>(dashboardService.createTask(task), HttpStatus.OK);
    }

}
