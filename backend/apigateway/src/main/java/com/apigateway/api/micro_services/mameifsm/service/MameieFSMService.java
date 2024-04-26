package com.apigateway.api.micro_services.mameifsm.service;

import com.apigateway.api.eureka.assets.table.MameieFSMRouterTable;
import com.apigateway.api.eureka.assets.table.SystemManagerRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Service class responsible for providing functionality related to the Mamei FSM (Frontend Service Manager).
 */
@Service
@RequiredArgsConstructor
public class MameieFSMService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    public List<Object>getStandardComponents() {
        String uri = clientService.getMameieFsmClientAdress() + MameieFSMRouterTable.GET_COMPONENT_STANDARD;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(List.class).block();
    }
}
