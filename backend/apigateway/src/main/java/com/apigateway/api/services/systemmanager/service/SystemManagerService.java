package com.apigateway.api.services.systemmanager.service;

import com.apigateway.api.discoveryclient.assets.table.SystemManagerRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import com.apigateway.api.services.securitygateway.service.SecurityGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SystemManagerService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;
    private final SecurityGatewayService securityGatewayService;

    @Async
    public String getPing() {
        String uri = clientService.getSystemClientAdress() + SystemManagerRouterTable.uri_system_ping;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }

}
