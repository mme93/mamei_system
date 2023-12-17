package com.systemmanager.session.service;

import com.systemmanager.session.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class SessionService {

    private WebClient webClient;
    private final DiscoveryClient discoveryClient;

    @PostConstruct
    private void setup() {
        this.webClient = WebClient.builder().build();
    }

    public String login(User user) {
        String apiGateWayURI = discoveryClient.getInstances("APIGATEWAY").get(0).getUri().toString();
        System.err.println(apiGateWayURI);
        webClient.post()
                .uri(apiGateWayURI + "/authenticate/login")
                .retrieve()
                .bodyToMono(Void.class);
        return "";

    }

    public void isTokenExpired(String token) {
        String loginBasedURI = discoveryClient.getInstances("LOGIN").get(0).getUri().toString();
        webClient.post()
                .uri(loginBasedURI + "/toke/isExpired")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
