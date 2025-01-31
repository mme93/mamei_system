package com.apigateway.api.micro_services.configmanager.service;

import com.apigateway.api.eureka.assets.table.ConfigManagerRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ConfigManagerService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    @Async
    public String ping() {
        String uri = clientService.getConfigManagerByName() + ConfigManagerRouterTable.CONFIG_MANAGER_PING;
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    @Async
    public Object getFilterConfig(String username, String frontendName) {
        String uri = String.format("%s%s%s%s",
                clientService.getConfigManagerByName(),
                ConfigManagerRouterTable.CONFIG_MANAGER_GET_FILTERCONFIG_PREFIX,
                frontendName,
                ConfigManagerRouterTable.CONFIG_MANAGER_GET_FILTERCONFIG_SUFFIX);
        return webClient
                .build()
                .get()
                .uri(uri)
                .headers(headers -> headers.set("X-Username", username))
                .retrieve()
                .bodyToMono(String.class).block();
    }
}
