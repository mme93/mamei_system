package com.securitygateway.security.service;

import com.securitygateway.security.model.dao.request.LoginRequest;
import com.securitygateway.security.model.dao.response.JwtAuthenticationResponse;
import com.securitygateway.security.model.entity.SearchUser;
import com.securitygateway.security.model.entity.UserFlags;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DiscoveryClient discoveryClient;
    private final WebClient.Builder webClient;

    public JwtAuthenticationResponse login(LoginRequest request) {
        UserDetails user = loadUser(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private UserDetails loadUser(LoginRequest request) {
        String uri = discoveryClient.getInstances("USER").get(0).getUri().toString();
        if (request.getFlag().equals(UserFlags.SYSTEM_USER)) {
            uri = uri + "/system_user/" + request.getUsername();
        } else if (request.getFlag().equals(UserFlags.USER)) {
            uri = uri + "/user/username/" + request.getUsername();
        } else {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        return webClient
                .build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(SearchUser.class).block();
    }


}
