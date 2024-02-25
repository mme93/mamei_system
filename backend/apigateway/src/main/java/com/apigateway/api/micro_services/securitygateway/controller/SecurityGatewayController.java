package com.apigateway.api.micro_services.securitygateway.controller;

import com.apigateway.api.micro_services.securitygateway.service.SecurityGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling security-related endpoints.
 */
@RestController
@RequestMapping("/security")
public class SecurityGatewayController {

    private final SecurityGatewayService securityGatewayService;

    @Autowired
    public SecurityGatewayController(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

    /**
     * Handles the login request.
     *
     * @param request The login request object.
     * @return ResponseEntity containing the result of the login operation.
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Object request) {
        return new ResponseEntity<>(securityGatewayService.login(request), HttpStatus.OK);
    }


    /**
     * Handles the registration request.
     *
     * @param request The registration request object.
     * @throws Exception if registration fails.
     */
    @PostMapping("/registration")
    public void registration(@RequestBody Object request) throws Exception {
        securityGatewayService.registration(request);
    }

}
