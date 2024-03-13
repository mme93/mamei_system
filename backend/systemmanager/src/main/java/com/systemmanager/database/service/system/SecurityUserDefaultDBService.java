package com.systemmanager.database.service.system;

import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static com.systemmanager.eureka.assets.table.ApiGatewayRouterTable.APIGATEWAY;

/**
 * Service class for managing default data reloading into the database.
 */
@Service
@RequiredArgsConstructor
public class SecurityUserDefaultDBService {

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;


    /**
     * Reloads default data into the database by sending a request to the API Gateway service.
     * @return true if the operation is successful, otherwise false
     */
    public boolean reloadDefaultDataIntoDatabase() {
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.DEFAULT_DB_RELOAD_SECURITY_USER;
        webClient
                .build()
                .post()
                .uri(apiGateURI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return true;
    }

}
