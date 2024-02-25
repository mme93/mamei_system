package com.apigateway.api.micro_services.dashboard.controller;

import com.apigateway.api.micro_services.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing dashboard-related HTTP requests.
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * Constructor for DashboardController.
     * @param dashboardService The service responsible for handling dashboard-related operations.
     */
    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    /**
     * Endpoint for checking the availability of the dashboard service.
     * @return A ResponseEntity containing the string "Ping" with HTTP status OK (200).
     */
    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new task.
     * @param task The task object to be created.
     * @return A ResponseEntity containing the response object from the DashboardService with HTTP status OK (200).
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createTask(@RequestBody Object task) {
        return new ResponseEntity<>(dashboardService.createTask(task), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a task by its ID.
     * @param id The ID of the task to retrieve.
     * @return A ResponseEntity containing the response object from the DashboardService with HTTP status OK (200).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        return new ResponseEntity<>(dashboardService.getTaskById(id), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving all tasks.
     * @return A ResponseEntity containing a list of tasks from the DashboardService with HTTP status OK (200).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Object>> getAllTask() {
        return new ResponseEntity<>(dashboardService.getAllTask(), HttpStatus.OK);
    }

}
