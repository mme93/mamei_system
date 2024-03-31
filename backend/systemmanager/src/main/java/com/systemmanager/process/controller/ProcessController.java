package com.systemmanager.process.controller;

import com.systemmanager.database.service.domains.ProcessDefaultDBService;
import com.systemmanager.process.model.process.ExecuteProcess;
import com.systemmanager.process.model.ui.ExecuteProcessUI;
import com.systemmanager.process.model.ui.ProcessElementUI;
import com.systemmanager.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing process´s.
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;
    private final ProcessDefaultDBService processDefaultDBService;

    /**
     * Constructor for ProcessController.
     *
     * @param processService         The ProcessService instance.
     * @param processDefaultDBService The ProcessDefaultDBService instance.
     */
    @Autowired
    public ProcessController(ProcessService processService, ProcessDefaultDBService processDefaultDBService) {
        this.processService = processService;
        this.processDefaultDBService = processDefaultDBService;
    }

    /**
     * Endpoint to start a new job.
     *
     * @param process The ExecuteProcess object containing job details.
     * @return ResponseEntity with a boolean indicating success.
     */
    @PostMapping("/newJob")
    public ResponseEntity<Boolean> startNewJob(@RequestBody ExecuteProcess process) {
        return new ResponseEntity<>(processService.startProcess(process), HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve the process list.
     *
     * @return ResponseEntity with a list of ProcessElementUI.
     */
    @GetMapping("/")
    public ResponseEntity<List<ProcessElementUI>> getProcessList() {
        return new ResponseEntity<>(processService.getProcessElementUI(), HttpStatus.OK);
    }


    /**
     * Endpoint to create a sorted ExecuteProcessUI.
     *
     * @param processList List of ProcessElementUI objects.
     * @return ResponseEntity with an ExecuteProcessUI object.
     */
    @PutMapping("/sort")
    public ResponseEntity<ExecuteProcessUI> createSortedExecuteProcessUI(@RequestBody List<ProcessElementUI> processList) {
        return new ResponseEntity(processService.createSortedExecuteProcessUI(processList), HttpStatus.OK);
    }

    /**
     * Endpoint to set default data.
     *
     * @return ResponseEntity with HTTP status OK.
     */
    @PostMapping("/set_default")
    public ResponseEntity setDefault() {
        processDefaultDBService.deleteAllDefaultData();
        processDefaultDBService.loadDefaultDataIntoDatabase();
        return new ResponseEntity(HttpStatus.OK);
    }

}
