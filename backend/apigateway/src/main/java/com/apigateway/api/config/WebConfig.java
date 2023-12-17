package com.apigateway.api.config;

import com.apigateway.api.services.securitygateway.service.SecurityGatewayService;
import com.apigateway.api.util.ApiGatewayInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SecurityGatewayService securityGatewayService;

    public WebConfig(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiGatewayInterceptor(securityGatewayService));
    }
}
