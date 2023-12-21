package com.securitygateway.security.service;

import com.securitygateway.security.model.entity.Role;
import com.securitygateway.security.model.entity.UserEntity;
import com.securitygateway.security.repository.UserRepository;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public boolean initUserEntityDatasets() {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        if (!userRepository.existsByEmail("guest@guest.de")) {

            userRepository.save(new UserEntity("guest", "guest", "gugu", "1223444", "guest@guest.de", pw.encode("test"), Role.USER));
        }

        if (!userRepository.existsByEmail("admin@admin.de")) {
            userRepository.save(new UserEntity("admin", "admin", "adad", "1223444", "admin@admin.de", pw.encode("test"), Role.ADMIN));
        }
        return userRepository.findAll().size() > 1;
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String encryptPassword(String password) {
        PasswordEncoder pw = new BCryptPasswordEncoder();
        return pw.encode(password);
    }
}
