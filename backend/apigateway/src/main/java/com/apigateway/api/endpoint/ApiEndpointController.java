package com.apigateway.api.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for API endpoint operations.
 */
@RestController
@RequestMapping("/endpoint")
@RequiredArgsConstructor
public class ApiEndpointController {

    private final RestartEndpoint restartEndpoint;

    /**
     * Restarts the application.
     */
    @GetMapping("/restart")
    public void restart(){
        restartEndpoint.restart();
    }

}
