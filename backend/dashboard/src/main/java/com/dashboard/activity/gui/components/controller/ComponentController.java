package com.dashboard.activity.gui.components.controller;

import com.dashboard.activity.gui.components.model.ComponentEntity;
import com.dashboard.activity.gui.components.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/component")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ComponentEntity> createComponent(@RequestBody ComponentEntity componentEntity){
        return new ResponseEntity<>(componentService.createComponent(componentEntity), HttpStatus.OK);
    }

}
