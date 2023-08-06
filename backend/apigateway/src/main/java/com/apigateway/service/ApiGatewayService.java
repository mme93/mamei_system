package com.apigateway.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class ApiGatewayService {

    @Async
    public String getTest1()throws InterruptedException{
        Thread.sleep(6000L);
        return "Test1";
    }

    @Async
    public String getTest2()throws InterruptedException{
        Thread.sleep(1000L);
        return "Test2";
    }

}
