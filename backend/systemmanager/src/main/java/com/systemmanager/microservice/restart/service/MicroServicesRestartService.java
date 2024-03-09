package com.systemmanager.microservice.restart.service;


import com.systemmanager.eureka.assets.EurekaDiscoveryClientNameTable;
import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.assets.table.DatastorageManagerRouteTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Locale;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class MicroServicesRestartService {

    private final DiscoveryClientService discoveryClientService;
    private final RestartEndpoint restartEndpoint;
    private final WebClient.Builder webClient;

    public boolean restartService(String microServiceName) {
        if (!EurekaDiscoveryClientNameTable.eurekaDiscoverClientNameList.contains(microServiceName)) {
            throw new NotFoundException("No Microservices found by Name: " + microServiceName);
        }
        if (!isOnWhiteList(microServiceName)) {
            if (!isEurekaServiceAvailable(microServiceName.toLowerCase(Locale.ROOT))) {
                throw new NotFoundException("No Microservices found as EurekaClient by Name: " + microServiceName);
            }
        }
        return callRestart(microServiceName);
    }


    public boolean isEurekaServiceAvailable(String microServiceName) {
        return discoveryClientService.existEurekaDiscoveryClientByName(microServiceName);
    }

    public boolean isOnWhiteList(String microServiceName) {
        return asList(
                EurekaDiscoveryClientNameTable.ApiGateWay,
                EurekaDiscoveryClientNameTable.SystemManagerAPI
        )
                .contains(microServiceName);
    }

    public boolean callRestart(String microServiceName) {
        switch (microServiceName) {
            case EurekaDiscoveryClientNameTable.SystemManagerAPI -> restartEndpoint.restart();
            case EurekaDiscoveryClientNameTable.DashboardAPI,
                    EurekaDiscoveryClientNameTable.GamesManager,
                    EurekaDiscoveryClientNameTable.HealthManagerAPI -> {
                return true;
            }
            case EurekaDiscoveryClientNameTable.ApiGateWay -> {
                return restartDataStorageApi(EurekaDiscoveryClientNameTable.ApiGateWay, ApiGatewayRouterTable.RESTART_END_POINT);
            }
            case EurekaDiscoveryClientNameTable.DataStorageAPI -> {
                return restartDataStorageApi(EurekaDiscoveryClientNameTable.DataStorageAPI, DatastorageManagerRouteTable.RESTART_END_POINT);
            }
            default -> throw new NotFoundException("No Microservice found by name: " + microServiceName);
        }
        return false;
    }

    public boolean restartDataStorageApi(String clientName, String restartEndpoint) {
        if (!discoveryClientService.existEurekaDiscoveryClientByName(clientName.toLowerCase(Locale.ROOT))) {
            return false;
        }
        String clientAdressByName = discoveryClientService.getClientAdressByName(clientName);
        String uri = clientAdressByName + restartEndpoint;
        Object result = webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
        return result.toString().equals("{message=Restarting}");
    }

}
