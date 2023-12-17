package com.apigateway.api.util;

import com.apigateway.api.services.securitygateway.service.SecurityGatewayService;
import com.apigateway.api.util.model.JwtToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static java.util.Arrays.asList;

public class ApiGatewayInterceptor implements HandlerInterceptor {

    private final SecurityGatewayService securityGatewayService;

    public ApiGatewayInterceptor(SecurityGatewayService securityGatewayService) {
        this.securityGatewayService = securityGatewayService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        if(!isInWhiteList(request.getRequestURI())){
            if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
            try{
                securityGatewayService.isTokeExpired(new JwtToken(authHeader));
            }catch (WebClientResponseException e){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // System.out.println("Nach dem Handler-Aufruf, vor der Rückgabe an den Client");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("Nach der Rückgabe an den Client oder bei einem Fehler");
    }

    private boolean isInWhiteList(String requestUri){
        List<String>whiteList=asList("/api/database/init");
        return whiteList.contains(requestUri);
    }
}
