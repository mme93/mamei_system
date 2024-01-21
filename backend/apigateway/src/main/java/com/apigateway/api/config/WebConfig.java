package com.apigateway.api.config;

import com.apigateway.api.micro_services.securitygateway.service.SecurityGatewayService;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SecurityGatewayService securityGatewayService;

    public WebConfig(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

}
