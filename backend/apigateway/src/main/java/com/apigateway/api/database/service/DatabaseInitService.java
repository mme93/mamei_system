package com.apigateway.api.database.service;

import com.apigateway.api.discoveryclient.assets.table.SecurityGatewayRouterTable;
import com.apigateway.api.discoveryclient.assets.table.UserRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class DatabaseInitService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;


    public void initDatabase() {
        System.err.println("Init Database");
        for(String uri:createList(asList(UserRouterTable.USER_ID, SecurityGatewayRouterTable.SECURITY_GATEWAY_ID))){
            callInit(uri);
        }
    }

    private List<String> createList(List<String>idList){
        List<String>uriList=new ArrayList<>();
        for(String id:idList){
            if(id.equals(UserRouterTable.USER_ID)){
                uriList.add(clientService.getUserClientAdress() + UserRouterTable.URI_USER_DATABASE_INIT);
            }else if(id.equals(SecurityGatewayRouterTable.SECURITY_GATEWAY_ID)){
                uriList.add(clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_USER_INIT);
            }
        }
        return uriList;
    }

    private Object callInit(String uri){
        return webClient
                .build()
                .post()
                .uri(uri)
                .retrieve()
                .bodyToMono(Object.class).block();
    }
}
