package com.apigateway.security.controller;

import com.apigateway.security.dao.request.RequestToken;
import com.apigateway.security.dao.response.JwtAuthenticationResponse;
import com.apigateway.security.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final JwtService jwtService;

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
