package com.apigateway.api.systemmanager.service;

import com.apigateway.api.discoveryclient.assets.table.SystemManagerRouterTable;
import com.apigateway.api.discoveryclient.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SystemManagerService {

    private final DiscoveryClientService clientService;
    private String systemClientAdress;
    private final WebClient.Builder webClient;


    @Async
    public String x(){
        String uri=clientService.getSystemClientAdress()+ SystemManagerRouterTable.uri_system_ping;
        String test=webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
        System.err.println("Mein test lautet: "+test);
        return "Ping";
    }

}
