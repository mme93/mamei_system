package com.apigateway.api.config;

import com.apigateway.api.micro_services.securitygateway.service.SecurityGatewayService;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for web-related configurations.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SecurityGatewayService securityGatewayService;

    /**
     * Constructs a new instance of WebConfig with SecurityGatewayService dependency injected.
     * @param securityGatewayService The SecurityGatewayService instance to be injected.
     */
    public WebConfig(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

}
