package com.apigateway.api.services.dashboard.controller;

import com.apigateway.api.services.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTask(@RequestBody Object task) {
        return new ResponseEntity<>(dashboardService.createTask(task), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(dashboardService.getTaskById(id), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllTask() {
        return new ResponseEntity<>(dashboardService.getAllTask(), HttpStatus.OK);
    }

}
