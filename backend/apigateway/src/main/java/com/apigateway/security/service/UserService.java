package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.dto.JwtToken;
import com.apigateway.security.model.entity.SecurityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final SecurityUserEntityRepository securityUserEntityRepository;
    private final JwtService jwtService;

    /**
     * Retrieves a UserDetailsService implementation for the specified username.
     *
     * @return A UserDetailsService implementation.
     */
    public UserDetailsService userDetailsService() {
        return username -> securityUserEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Loads a user by username.
     *
     * @param securityUserEntity The SecurityUserEntity object containing the username.
     * @return An optional containing the SecurityUserEntity if found, empty otherwise.
     */
    public Optional<SecurityUserEntity> loadUser(SecurityUserEntity securityUserEntity) {
        return securityUserEntityRepository.findByUsername(securityUserEntity.getUsername());
    }

    /**
     * Loads all security users from the database.
     *
     * @return A list of SecurityUserEntity objects containing all loaded users.
     */
    public List<SecurityUserEntity> loadUsers() {
        return securityUserEntityRepository.findAll();
    }

    /**
     * Generates a new JWT token based on an existing token.
     *
     * @param jwtToken The existing JWT token.
     * @return The new JWT token.
     */
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

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @return The username of the currently authenticated user, or null if not authenticated.
     */
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    /**
     * Retrieves the ID of the currently authenticated user.
     *
     * @return The ID of the currently authenticated user, or -1 if not authenticated or user not found.
     */
    public int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<SecurityUserEntity>user=securityUserEntityRepository.findByUsername(authentication.getName());
        if (authentication != null && authentication.isAuthenticated() && user.isPresent()) {
            return user.get().getId();
        }
        return -1;
    }

}
