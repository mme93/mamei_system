package com.apigateway.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    @PostMapping("/isExpired")
    public void isTokenExpired(){
        System.err.println("Test test test");
    }

}
