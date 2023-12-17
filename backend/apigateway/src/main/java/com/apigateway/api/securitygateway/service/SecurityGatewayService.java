package com.apigateway.api.securitygateway.service;

import com.apigateway.api.discoveryclient.assets.table.SecurityGatewayRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.api.securitygateway.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SecurityGatewayService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    @Async
    public Object login(LoginRequest request) {
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.uri_security_gateway_login;
        return webClient
                .build()
                .post()
                .uri(uri)
                //.contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(Object.class).block();
    }


}
