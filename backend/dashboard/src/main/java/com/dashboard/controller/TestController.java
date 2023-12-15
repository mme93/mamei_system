package com.dashboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping( "/hellos" )
    public String echo() {
        System.out.println("Hallo ich bin eine Hello!!!! Test mich weiter");
        return "Hello World! Ich wurde erweitert!";
    }

    @RequestMapping( "/helloWorld" )
    public String echos() {
        return "XXXX1";
    }

    @RequestMapping( "/test" )
    public String testset() {
        return "XXysadasdasdasdasdXX";
    }

    @RequestMapping( "/testD" )
    public String testset1() {
        return "Test mich";
    }
}
