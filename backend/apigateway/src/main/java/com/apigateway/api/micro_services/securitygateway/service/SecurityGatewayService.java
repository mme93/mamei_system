package com.apigateway.api.micro_services.securitygateway.service;

import com.apigateway.api.eureka.discoveryclient.assets.table.SecurityGatewayRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
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
    public Object isTokeExpired(Object token) throws Exception{
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_IS_EXPIRED;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(token))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    @Async
    public Object registration(Object object) throws Exception{
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_REGISTRATION;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(object))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    @Async
    public Object login(Object request) {
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_LOGIN;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(Object.class).block();
    }


}
