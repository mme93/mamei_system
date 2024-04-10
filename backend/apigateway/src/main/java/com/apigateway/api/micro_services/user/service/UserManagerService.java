package com.apigateway.api.micro_services.user.service;

import com.apigateway.api.eureka.assets.table.UserRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import com.apigateway.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * Service class responsible for interacting with the user manager microservice.
 */
@Service
@RequiredArgsConstructor
public class UserManagerService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;
    private final UserService userService;

    public Optional<Object> getAccountByUserId() {
        String uri = clientService.getUserClientAdress() + UserRouterTable.ACCOUNT_BY_USER_ID+userService.getCurrentUserId();
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Optional.class).block();
    }
}
