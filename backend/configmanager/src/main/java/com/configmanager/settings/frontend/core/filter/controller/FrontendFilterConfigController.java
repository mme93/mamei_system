package com.configmanager.settings.frontend.core.filter.controller;

import com.configmanager.settings.frontend.core.filter.service.FrontendFilterConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frontend/config/{frontendName}/filter")
public class FrontendFilterConfigController {

    private final FrontendFilterConfigService filterConfigService;

    public FrontendFilterConfigController(FrontendFilterConfigService filterConfigService) {
        this.filterConfigService = filterConfigService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getFilters() {

        return null;
    }

    @GetMapping("/{filterName}")
    public ResponseEntity<String> getFilter(@PathVariable String filterName, @PathVariable String frontendName) {
        System.err.println(frontendName);
        System.err.println(filterName);
        return null;
    }


}
