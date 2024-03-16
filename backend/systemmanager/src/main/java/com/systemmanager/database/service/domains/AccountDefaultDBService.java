package com.systemmanager.database.service.domains;

import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import com.systemmanager.token.model.JwtToken;
import com.systemmanager.token.model.SecurityUser;
import com.systemmanager.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.systemmanager.eureka.assets.table.ApiGatewayRouterTable.APIGATEWAY;

@Service
@RequiredArgsConstructor
public class AccountDefaultDBService implements IDefaultDBService {

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;
    private final TokenService tokenService;


    private List<SecurityUser> loadSecurityUser() {
        JwtToken jwtToken = tokenService.generateToken();
        jwtToken.setSignature();
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.LOAD_ALL_SECURITY_USER;

        return webClient
                .build()
                .get()
                .uri(apiGateURI)
                .headers(httpHeaders -> httpHeaders.add("Authorization", jwtToken.getToken()))
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }

    @Override
    public boolean loadDefaultDataIntoDatabase() {
        List<SecurityUser>securityUserList=loadSecurityUser();
        System.err.println(securityUserList.size());

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
