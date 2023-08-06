package com.systemmanager.service;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SystemSecurityService {

    private WebClient webClient;
    private final DiscoveryClient discoveryClient;


    @PostConstruct
    private void setup() {
        this.webClient = WebClient.builder().build();
    }

    public void isTokenExpired(String token) {
        String loginBasedURI = discoveryClient.getInstances("LOGIN").get(0).getUri().toString();
        webClient.post()
                .uri(loginBasedURI+"/toke/isExpired")
                .header("Authorization",token)
                .retrieve()
                .bodyToMono(Void.class);
    }


}
