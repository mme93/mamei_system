package com.login.controller;

import com.login.dao.request.RequestToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    @PostMapping("/isExpired")
    public void isTokenExpired(){
        System.err.println("Test test test");
    }

}
