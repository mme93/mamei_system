package com.apigateway.api.securitygateway.controller;

import com.apigateway.api.securitygateway.model.LoginRequest;
import com.apigateway.api.securitygateway.service.SecurityGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void login(@RequestBody LoginRequest request){
        securityGatewayService.login(request);
    }

}
