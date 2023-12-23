package com.apigateway.security.service;

import com.apigateway.security.SecurityUserEntityRepository;
import com.apigateway.security.model.entity.SecurityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SecurityUserEntityRepository securityUserEntityRepository;

    public UserDetailsService userDetailsService() {
        return username -> securityUserEntityRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Optional<SecurityUserEntity> loadUser(SecurityUserEntity securityUserEntity){
        return securityUserEntityRepository.findByUsername(securityUserEntity.getUsername());
    }

}
