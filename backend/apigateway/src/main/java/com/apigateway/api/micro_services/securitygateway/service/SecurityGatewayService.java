package com.apigateway.api.micro_services.securitygateway.service;

import com.apigateway.api.eureka.assets.table.SecurityGatewayRouterTable;
import com.apigateway.api.eureka.service.DiscoveryClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Service class responsible for handling security-related operations.
 */
@Service
@RequiredArgsConstructor
public class SecurityGatewayService {

    private final DiscoveryClientService clientService;
    private final WebClient.Builder webClient;

    /**
     * Asynchronously checks if a token is expired.
     *
     * @param token The token object to be checked.
     * @return Object representing the result of the token expiration check.
     * @throws Exception if an error occurs during the operation.
     */
    @Async
    public Object isTokeExpired(Object token) throws Exception {
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_IS_EXPIRED;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(token))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Asynchronously handles the registration process.
     *
     * @param object The registration request object.
     * @return Object representing the result of the registration process.
     * @throws Exception if an error occurs during the registration process.
     */
    @Async
    public Object registration(Object object) throws Exception {
        String uri = clientService.getSecurityGatewayClientAdress() + SecurityGatewayRouterTable.URI_SECURITY_GATEWAY_REGISTRATION;
        return webClient
                .build()
                .post()
                .uri(uri)
                .body(BodyInserters.fromValue(object))
                .retrieve()
                .bodyToMono(Object.class).block();
    }

    /**
     * Asynchronously handles the login process.
     *
     * @param request The login request object.
     * @return Object representing the result of the login process.
     */
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
