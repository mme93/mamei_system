package com.apigateway.api.micro_services.massdatapool.controller;

import com.apigateway.api.micro_services.massdatapool.service.MassDataPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/massdatapool")
public class MassDataPoolController {

    private final MassDataPoolService massDataPoolService;

    @Autowired
    public MassDataPoolController(MassDataPoolService massDataPoolService) {
        this.massDataPoolService = massDataPoolService;
    }

    @GetMapping("/log/info")
    public ResponseEntity<byte[]> getInfoLog(){
        return new ResponseEntity<>(massDataPoolService.getInfoLog(), HttpStatus.OK);
    }

}
