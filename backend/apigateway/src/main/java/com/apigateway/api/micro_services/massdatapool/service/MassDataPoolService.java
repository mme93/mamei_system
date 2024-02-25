package com.apigateway.api.micro_services.massdatapool.service;

import com.apigateway.api.eureka.assets.table.MassDataPoolRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service class responsible for interacting with the Mass Data Pool service.
 */
@Service
@RequiredArgsConstructor
public class MassDataPoolService {

    private final WebClient.Builder webClient;
    private final DiscoveryClientService clientService;

    /**
     * Retrieves the information log from the Mass Data Pool service.
     * @return Byte array containing the information log retrieved from the Mass Data Pool service.
     */
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
