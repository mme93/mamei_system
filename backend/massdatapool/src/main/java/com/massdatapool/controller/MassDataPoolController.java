package com.massdatapool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MassDataPoolController {

    @GetMapping("/test")
    public void test(){
        int a=Integer.valueOf("Test");
    }

}
