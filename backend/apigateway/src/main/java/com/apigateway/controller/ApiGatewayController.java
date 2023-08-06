package com.apigateway.controller;

import com.apigateway.service.ApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiGatewayController {

    private final ApiGatewayService apiGatewayService;

    @Autowired
    public ApiGatewayController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @GetMapping("/test1")
    public String getTest1() throws InterruptedException {
        return apiGatewayService.getTest1();
    }

    @GetMapping("/test2")
    public String getTest2() throws InterruptedException {
        return apiGatewayService.getTest2();
    }

}
