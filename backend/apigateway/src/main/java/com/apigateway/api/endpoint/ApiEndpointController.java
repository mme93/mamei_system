package com.apigateway.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpoint")
@RequiredArgsConstructor
public class ApiEndpointController {

    private final RestartEndpoint restartEndpoint;

    @GetMapping("/restart")
    public void restart(){
        restartEndpoint.restart();
    }

}
