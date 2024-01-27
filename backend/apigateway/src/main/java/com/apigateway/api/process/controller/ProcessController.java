package com.apigateway.api.process.controller;

import com.apigateway.api.process.model.ExecuteProcess;
import com.apigateway.api.process.model.Process;
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

    @Autowired
    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }



    @PostMapping("/newJob")
    public ResponseEntity<Boolean> startNewJob(@RequestBody ExecuteProcess process){
        try {
            Thread.sleep(3000);
            return new ResponseEntity<>(processService.startProcess(process),HttpStatus.OK);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity("Job finished",HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Process>>getProcessList(){
        return new ResponseEntity<>(processService.getProcesses(),HttpStatus.OK);
    }

    @PutMapping("/sort")
    public ResponseEntity<List<Process>>sortProcessList(@RequestBody List<Process> processList){
        return new ResponseEntity(processService.sortProcessList(processList),HttpStatus.OK);
    }

}
