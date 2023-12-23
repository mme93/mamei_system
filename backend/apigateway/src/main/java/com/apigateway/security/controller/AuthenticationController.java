package com.apigateway.security.controller;

import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.service.AuthenticationService;
import com.apigateway.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SecurityUserEntity securityUserEntity) {
        Optional<SecurityUserEntity> userOpt = userService.loadUser(securityUserEntity);
        if (userOpt.isPresent()) {
            return new ResponseEntity<>(authenticationService.login(securityUserEntity), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody SecurityUserEntity securityUserEntity) {
        if (authenticationService.registration(securityUserEntity)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

}
