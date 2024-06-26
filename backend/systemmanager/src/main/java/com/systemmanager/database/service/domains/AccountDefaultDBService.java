package com.systemmanager.database.service.domains;

import com.systemmanager.eureka.assets.table.AccountRouterTable;
import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import com.systemmanager.token.model.JwtToken;
import com.systemmanager.token.model.SecurityUser;
import com.systemmanager.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        String apiGateURI = discoveryClientService.getClientAddressByName(APIGATEWAY) + ApiGatewayRouterTable.LOAD_ALL_SECURITY_USER;

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

        String userURI = discoveryClientService.getClientAddressByName(AccountRouterTable.USER) + AccountRouterTable.CREATE_DEFAULT_ACCOUNT_DATASET;
        webClient
                .build()
                .post()
                .uri(userURI)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return false;
    }

    @Override
    public boolean deleteAllData() {

        String userURI = discoveryClientService.getClientAddressByName(AccountRouterTable.USER) + AccountRouterTable.DELETE_ALL_ACCOUNT_DATA;
        webClient
                .build()
                .delete()
                .uri(userURI)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        return false;
    }

    @Override
    public boolean deleteAllDefaultData() {
        String userURI = discoveryClientService.getClientAddressByName(AccountRouterTable.USER) + AccountRouterTable.DELETE_ALL_ACCOUNT_DATA;
        webClient
                .build()
                .delete()
                .uri(userURI)
                .retrieve()
                .bodyToMono(Boolean.class)
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
