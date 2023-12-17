package com.apigateway.api.services.securitygateway.controller;

import com.apigateway.api.services.securitygateway.model.LoginRequest;
import com.apigateway.api.services.securitygateway.service.SecurityGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityGatewayController {

    private final SecurityGatewayService securityGatewayService;

    @Autowired
    public SecurityGatewayController(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Object request){
        return new ResponseEntity<>(securityGatewayService.login(request), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody Object request) throws Exception {
        securityGatewayService.registration(request);
    }

}
