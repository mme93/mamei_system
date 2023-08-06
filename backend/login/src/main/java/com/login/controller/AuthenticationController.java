package com.login.controller;

import com.login.dao.request.RegistrationsRequest;
import com.login.dao.request.LoginRequest;
import com.login.dao.response.JwtAuthenticationResponse;
import com.login.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<JwtAuthenticationResponse> registration(@RequestBody RegistrationsRequest request) {
        return ResponseEntity.ok(authenticationService.registration(request));
    }

}
