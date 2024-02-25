package com.apigateway.api.micro_services.massdatapool.controller;

import com.apigateway.api.micro_services.massdatapool.service.MassDataPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling MassDataPool (MDP) related endpoints.
 */
@RestController
@RequestMapping("/massdatapool")
public class MassDataPoolController {

    private final MassDataPoolService massDataPoolService;

    /**
     * Constructor injection to initialize the MassDataPoolController with a MassDataPoolService instance.
     * @param massDataPoolService The service responsible for providing functionality related to the mass data pool.
     */
    @Autowired
    public MassDataPoolController(MassDataPoolService massDataPoolService) {
        this.massDataPoolService = massDataPoolService;
    }

    /**
     * Endpoint to retrieve information log from the mass data pool service.
     * @return ResponseEntity containing the information log as byte array with HTTP status OK.
     */
    @GetMapping("/log/info")
    public ResponseEntity<byte[]> getInfoLog(){
        return new ResponseEntity<>(massDataPoolService.getInfoLog(), HttpStatus.OK);
    }

}
