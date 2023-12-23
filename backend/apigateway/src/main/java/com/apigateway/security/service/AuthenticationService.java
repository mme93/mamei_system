package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecurityUserEntityRepository securityUserEntityRepository;


    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, SecurityUserEntityRepository securityUserEntityRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.securityUserEntityRepository = securityUserEntityRepository;
    }

    public String login(SecurityUserEntity securityUserEntity) {
        var user = securityUserEntityRepository.findByUsername(securityUserEntity.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        return jwtService.generateToken(user);
    }

    public boolean registration(SecurityUserEntity securityUserEntity) {
        try {
            securityUserEntityRepository.save(securityUserEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
