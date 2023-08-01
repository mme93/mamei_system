package com.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    private final DiscoveryClient discoveryClient;

    @Autowired
    public LoginService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public String getTest() {
        for(String service: discoveryClient.getServices()){
            System.err.println(service);
        }
        return discoveryClient.getServices().get(0).toString();
    }
}
