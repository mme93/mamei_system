package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.dto.JwtToken;
import com.apigateway.security.model.entity.SecurityUserEntity;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SecurityUserEntityRepository securityUserEntityRepository;
    private final JwtService jwtService;

    public UserDetailsService userDetailsService() {
        return username -> securityUserEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<SecurityUserEntity> loadUser(SecurityUserEntity securityUserEntity) {
        return securityUserEntityRepository.findByUsername(securityUserEntity.getUsername());
    }

    public JwtToken generateNewToken(JwtToken jwtToken) {
        String token = jwtToken.getToken();
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        if (jwtService.isTokenExpired(token)) {
            throw new RuntimeException("Token is expired: " + token);
        }
        String userName = jwtService.extractUserName(token);
        return new JwtToken(jwtService.generateToken(securityUserEntityRepository.findByUsername(userName).get()));
    }

}
