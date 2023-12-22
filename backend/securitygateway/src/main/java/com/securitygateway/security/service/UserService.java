package com.securitygateway.security.service;

import com.securitygateway.security.model.entity.Role;
import com.securitygateway.security.model.entity.SearchUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                System.err.println(username);
                return new SearchUser("gugus","$2a$10$.9EXnejKjkgTNbipq.ZF5.I13i6yxPl3jJmN7XZ.JnP.MUAOVoOry", Role.USER);
            }
        };
    }

}
