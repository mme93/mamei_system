package com.systemmanager.eureka.service;

import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

/**
 * Service class for interacting with Eureka Discovery Client.
 */
@Service
@RequiredArgsConstructor
public class DiscoveryClientService {

    private final DiscoveryClient discoveryClient;

    /**
     * Get the address of a client by its name.
     * @param clientName the name of the client
     * @return the address of the client
     */
    public String getClientAdressByName(String clientName){
        return discoveryClient.getInstances(clientName).get(0).getUri().toString();
    }

    /**
     * Check if an Eureka Discovery Client exists by its name.
     * @param name the name of the client
     * @return true if the client exists, otherwise false
     */
    public boolean existEurekaDiscoveryClientByName(String name){
        return discoveryClient.getServices().stream().filter(microserviceName-> microserviceName.equals(name)).count()==1;
    }

}
