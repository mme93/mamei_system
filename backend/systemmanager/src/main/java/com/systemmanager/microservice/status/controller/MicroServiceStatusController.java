package com.systemmanager.microservice.status.controller;

import com.systemmanager.microservice.status.model.dto.MicroServiceDto;
import com.systemmanager.microservice.status.service.MicroServiceStatusService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for managing microservice status.
 */
@RestController
@RequestMapping("/service_status")
public class MicroServiceStatusController {

    private final MicroServiceStatusService microServiceStatusService;

    /**
     * Constructs a MicroServiceStatusController object with the specified MicroServiceStatusService.
     * @param microServiceStatusService the microservice status service to be injected
     */
    @Autowired
    public MicroServiceStatusController(MicroServiceStatusService microServiceStatusService) {
        this.microServiceStatusService = microServiceStatusService;
    }

    /**
     * Retrieves the status of all microservices.
     * @return a ResponseEntity containing a list of MicroServiceDto and HTTP status OK
     */
    @GetMapping("")
    public ResponseEntity<List<MicroServiceDto>> getMicroServicesStatus() {
        return new ResponseEntity<>(microServiceStatusService.getMicroServicesStatus(), HttpStatus.OK);
    }

    /**
     * Retrieves the status of a specific microservice.
     * @param microServiceName the name of the microservice
     * @return a ResponseEntity containing the MicroServiceDto for the specified microservice and HTTP status OK,
     *         or HTTP status NOT_FOUND if the microservice is not found
     */
    @GetMapping("/{microServiceName}")
    public ResponseEntity<MicroServiceDto> getMicroServiceStatus(@PathVariable String microServiceName) {
        try {
            return new ResponseEntity<>(microServiceStatusService.getMicroServiceStatus(microServiceName), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
