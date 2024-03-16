package com.systemmanager.database.service.domains;

import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static com.systemmanager.eureka.assets.table.ApiGatewayRouterTable.APIGATEWAY;

@Service
@RequiredArgsConstructor
public class PrivilegesDefaultDBService implements IDefaultDBService{

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;

    @Override
    public boolean loadDefaultDataIntoDatabase() {
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.DEFAULT_DB_RELOAD_SECURITY_USER;
        webClient
                .build()
                .post()
                .uri(apiGateURI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return false;
    }

    @Override
    public boolean deleteAllData() {
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.DEFAULT_DB_RELOAD_SECURITY_USER;
        webClient
                .build()
                .post()
                .uri(apiGateURI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return false;
    }

    @Override
    public boolean deleteAllDefaultData() {
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.DEFAULT_DB_RELOAD_SECURITY_USER;
        webClient
                .build()
                .post()
                .uri(apiGateURI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return false;
    }

    @Override
    public boolean existTable() {
        return false;
    }

    @Override
    public boolean deleteTable() {
        return false;
    }

    @Override
    public boolean createTable() {
        return false;
    }
}
