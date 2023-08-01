package com.login.controller;

import com.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/helloWorld")
    public ResponseEntity<String> getHelloWorld() {

        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        return new ResponseEntity<>(loginService.getTest(), HttpStatus.OK);
    }



}
