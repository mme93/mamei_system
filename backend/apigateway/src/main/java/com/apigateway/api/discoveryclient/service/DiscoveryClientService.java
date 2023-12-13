package com.apigateway.api.discoveryclient.service;

import com.apigateway.api.discoveryclient.assets.EurekaDiscoveryClientNameTable;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscoveryClientService {

    private final DiscoveryClient discoveryClient;

    public String getSudokuClientAdress(){
       return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SudokuAPI).get(0).getUri().toString();
    }

}