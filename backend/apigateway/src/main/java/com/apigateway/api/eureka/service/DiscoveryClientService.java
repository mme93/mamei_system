package com.apigateway.api.eureka.service;

import com.apigateway.api.eureka.assets.EurekaDiscoveryClientNameTable;
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

    public String getSystemClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SystemManagerAPI).get(0).getUri().toString();
    }

    public String getSecurityGatewayClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.SecurityGatewayAPI).get(0).getUri().toString();
    }

    public String getUserClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.UserAPI).get(0).getUri().toString();
    }

    public String getDashboardClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.DashboardAPI).get(0).getUri().toString();
    }

    public String getGamesManagerClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.GamesManager).get(0).getUri().toString();
    }

    public String getMameieFsmClientAdress(){
        return discoveryClient.getInstances(EurekaDiscoveryClientNameTable.Mameie_FSM).get(0).getUri().toString();
    }


    public boolean existEurekaDiscoveryClientByName(String name){
        discoveryClient.getServices().forEach(x-> System.err.println(x));

        return true;
    }

}
