package com.apigateway.microservice.restart.service;

import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MicroServicesRestartService {

    private final DiscoveryClientService discoveryClientService;

    @Autowired
    public MicroServicesRestartService(DiscoveryClientService discoveryClientService) {
        this.discoveryClientService = discoveryClientService;
    }

    public boolean restartService(String microServiceName) {
        if (!EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList.contains(microServiceName)) {
            throw new NotFoundException("No Microservices found by Name: " + microServiceName);
        }
        if (!isEurekaServiceAvailable(microServiceName)) {
            throw new NotFoundException("No Microservices found as EurekaClient by Name: " + microServiceName);
        }
        return callRestart(microServiceName);
    }

    public boolean callRestart(String microServiceName) {

        return true;
    }

    public boolean isEurekaServiceAvailable(String microServiceName) {
        return discoveryClientService.existEurekaDiscoveryClientByName(microServiceName);
    }
}
