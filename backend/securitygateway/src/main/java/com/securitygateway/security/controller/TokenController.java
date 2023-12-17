package com.securitygateway.security.controller;

import com.securitygateway.security.model.dao.request.RequestToken;
import com.securitygateway.security.model.dao.response.JwtAuthenticationResponse;
import com.securitygateway.security.service.JwtService;
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
