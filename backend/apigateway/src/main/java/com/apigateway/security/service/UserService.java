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


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    public int getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<SecurityUserEntity>user=securityUserEntityRepository.findByUsername(authentication.getName());
        if (authentication != null && authentication.isAuthenticated() && user.isPresent()) {
            return user.get().getId();
        }
        return -1;
    }

}
