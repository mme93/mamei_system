package com.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping( "/hellos" )
    public String echo() {
        System.out.println("Hallo ich bin eine Hello!!!!");
        return "Hello World! Ich wurde erweitert!";
    }

    @RequestMapping( "/helloWorld" )
    public String echos() {
        return "XXXX";
    }
}
