package com.configmanager.settings.frontend.core.controller;

import com.configmanager.settings.frontend.core.service.FrontendConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frontend/config")
public class FrontendConfigController {

    private final FrontendConfigService frontendConfigService;

    public FrontendConfigController(FrontendConfigService frontendConfigService) {
        this.frontendConfigService = frontendConfigService;
    }

    @GetMapping("/{frontendName}")
    public ResponseEntity getFrontendConfig(@RequestHeader(value = "X-Username", required = false) String username, @PathVariable String frontendName){
        return new ResponseEntity(frontendConfigService.getFrontendConfig(username,frontendName),HttpStatus.OK);
    }

}
