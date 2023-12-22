package com.securitygateway.security.controller;

import com.securitygateway.security.model.dao.request.LoginRequest;
import com.securitygateway.security.model.dao.request.RequestToken;
import com.securitygateway.security.model.dao.response.JwtAuthenticationResponse;
import com.securitygateway.security.service.AuthenticationService;
import com.securitygateway.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }


    @PostMapping("/isExpired")
    public ResponseEntity<JwtAuthenticationResponse> isTokenExpired(@RequestBody RequestToken requestToken) {
        try {
            String token = requestToken.getToken().substring(7);
            jwtService.isTokenExpired(token);
            token = jwtService.generateNewToken(new HashMap<>(), token);
            return new ResponseEntity(new JwtAuthenticationResponse(token), HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

}
