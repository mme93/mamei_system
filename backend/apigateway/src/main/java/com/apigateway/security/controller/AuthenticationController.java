package com.apigateway.security.controller;

import com.apigateway.security.model.dto.JwtToken;
import com.apigateway.security.model.entity.SecurityUserEntity;
import com.apigateway.security.service.AuthenticationService;
import com.apigateway.security.service.JwtService;
import com.apigateway.security.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller class handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    /**
     * Constructor for AuthenticationController.
     * @param userService UserService object for user-related operations.
     * @param authenticationService AuthenticationService object for authentication-related operations.
     * @param jwtService JwtService object for JWT-related operations.
     */
    @Autowired
    public AuthenticationController(UserService userService, AuthenticationService authenticationService, JwtService jwtService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    /**
     * Endpoint for user login.
     * @param securityUserEntity SecurityUserEntity object representing the user credentials.
     * @return ResponseEntity containing a JwtToken if login is successful, or NOT_FOUND status if user not found.
     */
    @PostMapping("/login")
    public ResponseEntity<JwtToken> login(@RequestBody SecurityUserEntity securityUserEntity) {
        Optional<SecurityUserEntity> userOpt = userService.loadUser(securityUserEntity);
        if (userOpt.isPresent()) {
            return new ResponseEntity<>(new JwtToken(authenticationService.login(securityUserEntity)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    /**
     * Endpoint for user registration.
     * @param securityUserEntity SecurityUserEntity object representing the user details.
     * @return ResponseEntity with OK status if registration is successful, or CONFLICT status if registration fails.
     */
    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody SecurityUserEntity securityUserEntity) {
        if (authenticationService.registration(securityUserEntity)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint for checking if a JWT token is expired.
     * @param jwtToken JwtToken object representing the JWT token.
     * @return ResponseEntity containing a new JwtToken if token is not expired, or FORBIDDEN status if token is expired.
     */
    @PostMapping("/isTokenExpired")
    public ResponseEntity<JwtToken> isTokenExpired(@RequestBody JwtToken jwtToken){
       try{
           return new ResponseEntity<>(userService.generateNewToken(jwtToken),HttpStatus.OK);
       }catch (RuntimeException e){
           return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }

    }

    /**
     * Endpoint for testing server availability.
     * @return ResponseEntity with a "Ping" message and OK status.
     */
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

}
