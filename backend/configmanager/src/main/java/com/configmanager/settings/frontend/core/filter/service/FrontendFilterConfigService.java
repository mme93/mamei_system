package com.configmanager.settings.frontend.core.filter.service;

import com.configmanager.settings.frontend.core.filter.model.FrontendFilterConfig;
import org.springframework.stereotype.Service;

@Service
public class FrontendFilterConfigService {

    public FrontendFilterConfig getFilterConfig(String frontendName, String username) {


        return new FrontendFilterConfig("DAs ist ein ConfigFilter");
    }
}
