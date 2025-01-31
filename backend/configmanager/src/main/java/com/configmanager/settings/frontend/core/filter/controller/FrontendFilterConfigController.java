package com.configmanager.settings.frontend.core.filter.controller;

import com.configmanager.settings.frontend.core.filter.model.FrontendFilterConfig;
import com.configmanager.settings.frontend.core.filter.service.FrontendFilterConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frontend/config/{frontendName}/filter")
public class FrontendFilterConfigController {

    private final FrontendFilterConfigService filterConfigService;

    public FrontendFilterConfigController(FrontendFilterConfigService filterConfigService) {
        this.filterConfigService = filterConfigService;
    }

    @GetMapping("/")
    public ResponseEntity<FrontendFilterConfig> getFilterConfig(@RequestHeader(value = "X-Username", required = false) String username, @PathVariable String frontendName) {
        return new ResponseEntity<>(filterConfigService.getFilterConfig(frontendName,username), HttpStatus.OK);
    }


}
