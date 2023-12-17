package com.securitygateway.security.controller;

import com.securitygateway.security.model.dao.request.LoginRequest;
import com.securitygateway.security.model.dao.request.RegistrationsRequest;
import com.securitygateway.security.model.dao.response.JwtAuthenticationResponse;
import com.securitygateway.security.service.AuthenticationService;

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
