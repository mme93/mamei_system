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
        return new ResponseEntity<>(processService.startProcess(process),HttpStatus.OK);
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
        processDefaultDBService.deleteAllDefaultData();
        processDefaultDBService.loadDefaultDataIntoDatabase();
        return new ResponseEntity(HttpStatus.OK);
    }

}
