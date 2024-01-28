package com.apigateway.microservice.restart.service;


import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;
import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class MicroServicesRestartService {

    private final DiscoveryClientService discoveryClientService;
    private final RestartEndpoint restartEndpoint;

    public boolean restartService(String microServiceName) {
        if (!EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList.contains(microServiceName)) {
            throw new NotFoundException("No Microservices found by Name: " + microServiceName);
        }
        if (!isOnWhiteList(microServiceName)) {
            if (!isEurekaServiceAvailable(microServiceName)) {
                throw new NotFoundException("No Microservices found as EurekaClient by Name: " + microServiceName);
            }
        }
        return callRestart(microServiceName);
    }

    public boolean callRestart(String microServiceName) {
        restartEndpoint.restart();
        return true;
    }

    public boolean isEurekaServiceAvailable(String microServiceName) {
        return discoveryClientService.existEurekaDiscoveryClientByName(microServiceName);
    }

    public boolean isOnWhiteList(String microServiceName) {
        return asList(
                EurekaDiscoveryClientNameTable.ApiGateWay
        )
                .contains(microServiceName);
    }
}
