package com.apigateway.api.micro_services.massdatapool.service;

import com.apigateway.api.eureka.assets.table.MassDataPoolRouterTable;
import com.apigateway.api.eureka.assets.table.SystemManagerRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class MassDataPoolService {

    private final WebClient.Builder webClient;
    private final DiscoveryClientService clientService;

    public byte[] getInfoLog() {
        String uri = clientService.getMassDataPoolClientAdress() + MassDataPoolRouterTable.LOAD_INFO_LOG;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(byte[].class).block();
    }
}
