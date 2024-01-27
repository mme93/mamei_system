package com.apigateway.microservice.status.controller;

import com.apigateway.microservice.status.model.dto.MicroServiceDto;
import com.apigateway.microservice.status.service.MicroServiceStatusService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service_status")
public class MicroServiceStatusController {

    private final MicroServiceStatusService microServiceStatusService;

    @Autowired
    public MicroServiceStatusController(MicroServiceStatusService microServiceStatusService) {
        this.microServiceStatusService = microServiceStatusService;
    }

    @GetMapping("")
    public ResponseEntity<List<MicroServiceDto>> getMicroServicesStatus() {
        return new ResponseEntity<>(this.microServiceStatusService.getMicroServicesStatus(), HttpStatus.OK);
    }

    @GetMapping("/{microServiceName}")
    public ResponseEntity<MicroServiceDto> getMicroServiceStatus(@PathVariable String microServiceName) {
        try {
            return new ResponseEntity<>(this.microServiceStatusService.getMicroServiceStatus(microServiceName), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
