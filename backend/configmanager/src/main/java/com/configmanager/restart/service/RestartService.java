package com.configmanager.restart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.restart.RestartEndpoint;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestartService {

    private final RestartEndpoint restartEndpoint;

    public Object restart() {
        return restartEndpoint.restart();
    }
}
