package com.mameie_fsm.gui.components.controller;


import com.mameie_fsm.gui.components.service.StandardComponentService;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/component")
public class ComponentController {

    private final StandardComponentService standardComponentService;

    @Autowired
    public ComponentController(StandardComponentService standardComponentService) {
        this.standardComponentService = standardComponentService;
    }

    @GetMapping("/standard")
    public ResponseEntity<List<StandardComponent>> getAllStandardComponents(){
        return new ResponseEntity(standardComponentService.getAllStandardComponents(), HttpStatus.OK);
    }
}
