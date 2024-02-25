package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user authentication and registration.
 */
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecurityUserEntityRepository securityUserEntityRepository;

    /**
     * Constructor for AuthenticationService.
     * @param authenticationManager The AuthenticationManager used for user authentication.
     * @param jwtService The JwtService used for JWT token generation and validation.
     * @param securityUserEntityRepository The repository for accessing SecurityUserEntity data.
     */
    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, SecurityUserEntityRepository securityUserEntityRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.securityUserEntityRepository = securityUserEntityRepository;
    }

    /**
     * Method to authenticate a user and generate a JWT token upon successful authentication.
     * @param securityUserEntity The SecurityUserEntity representing the user's credentials.
     * @return The JWT token generated upon successful authentication.
     * @throws IllegalArgumentException if the provided username or password is invalid.
     */
    public String login(SecurityUserEntity securityUserEntity) {
        var user = securityUserEntityRepository.findByUsername(securityUserEntity.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        return jwtService.generateToken(user);
    }

    /**
     * Method to register a new user.
     * @param securityUserEntity The SecurityUserEntity representing the user to be registered.
     * @return true if the user is successfully registered, false otherwise.
     */
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
