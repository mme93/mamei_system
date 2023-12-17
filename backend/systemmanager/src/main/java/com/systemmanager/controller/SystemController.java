package com.systemmanager.controller;

import com.systemmanager.model.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class SystemController {

    @GetMapping("/ping")
    public ResponseEntity<String> getPing(){
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

    @GetMapping("/time")
    public ResponseEntity<String> getAfterTimePing() throws InterruptedException {

        long waitTimeMillis = 10 * 1000;
        Thread.sleep(waitTimeMillis);
        return new ResponseEntity<>("Ping after 10sec", HttpStatus.OK);
    }

    @PostMapping("/ping")
    public ResponseEntity<LoginRequest> postX(@RequestBody LoginRequest request){

        return new ResponseEntity<>(request,HttpStatus.OK);
    }

}
