package com.dashboard.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscoveryClientService {

    private final DiscoveryClient discoveryClient;

    public String getConfigManagerByName() {
        return discoveryClient.getInstances("CONFIGMANAGER").get(0).getUri().toString();
    }

}
