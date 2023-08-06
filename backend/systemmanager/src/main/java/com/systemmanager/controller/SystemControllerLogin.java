package com.systemmanager.controller;

import com.systemmanager.model.RequestToken;
import com.systemmanager.service.SystemSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SystemControllerLogin {

    private final SystemSecurityService systemSecurityService;

    @PostMapping("/helloworld")
    public String getHelloworld(@RequestBody RequestToken requestToken){
        System.err.println("HelloWorld");
        systemSecurityService.isTokenExpired(requestToken.getToken());
        return "Hello World";
    }

}
