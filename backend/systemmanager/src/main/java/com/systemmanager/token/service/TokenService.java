package com.systemmanager.token.service;

import com.systemmanager.eureka.assets.table.ApiGatewayRouterTable;
import com.systemmanager.eureka.service.DiscoveryClientService;
import com.systemmanager.token.model.JwtToken;
import com.systemmanager.token.model.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

import static com.systemmanager.eureka.assets.table.ApiGatewayRouterTable.APIGATEWAY;

/**
 * Service class for handling token generation.
 */
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${securityUser.password}")
    private String password;

    @Value("${securityUser.username}")
    private String userName;

    @Value("${securityUser.userCollection}")
    private String userCollection;

    private final DiscoveryClientService discoveryClientService;
    private final WebClient.Builder webClient;


    /**
     * Generates a JWT token.
     *
     * @return JwtToken containing the generated token.
     */
    public JwtToken generateToken() {
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        SecurityUser securityUser = new SecurityUser(userName,decodedPassword,userCollection);
        String apiGateURI = discoveryClientService.getClientAdressByName(APIGATEWAY) + ApiGatewayRouterTable.GENERATE_TOKEN;
        return webClient
                .build()
                .post()
                .uri(apiGateURI)
                .body(BodyInserters.fromValue(securityUser))
                .retrieve()
                .bodyToMono(JwtToken.class)
                .block();
    }
}
