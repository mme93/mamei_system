package com.apigateway.api.process.controller;

import com.apigateway.api.database.service.domains.ProcessDefaultDBService;
import com.apigateway.api.process.model.process.ExecuteProcess;
import com.apigateway.api.process.model.ui.ExecuteProcessUI;
import com.apigateway.api.process.model.ui.ProcessElementUI;
import com.apigateway.api.process.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ProcessService processService;
    private final ProcessDefaultDBService processDefaultDBService;

    @Autowired
    public ProcessController(ProcessService processService, ProcessDefaultDBService processDefaultDBService) {
        this.processService = processService;
        this.processDefaultDBService = processDefaultDBService;
    }

    @PostMapping("/newJob")
    public ResponseEntity<Boolean> startNewJob(@RequestBody ExecuteProcess process){
        System.err.println(process.getTaskSignature());
        try {
            Thread.sleep(3000);
            return new ResponseEntity<>(processService.startProcess(process),HttpStatus.OK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity("Job finished",HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<ProcessElementUI>>getProcessList(){
        return new ResponseEntity<>(processService.getProcessElementUI(),HttpStatus.OK);
    }

    @PutMapping("/sort")
    public ResponseEntity<ExecuteProcessUI>createSortedExecuteProcessUI(@RequestBody List<ProcessElementUI> processList){
        return new ResponseEntity(processService.createSortedExecuteProcessUI(processList),HttpStatus.OK);
    }

    @PostMapping("/set_default")
    public ResponseEntity setDefault(){
        processDefaultDBService.loadDefaultDataIntoDatabase();
        return new ResponseEntity(HttpStatus.OK);
    }

}
