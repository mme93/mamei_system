package com.mameie_fsm.database.controller;

import com.mameie_fsm.database.service.DefaultDatabaseStandardComponentService;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/default_database/standard_component")
public class DefaultDatabaseStandardComponentController {


    private final DefaultDatabaseStandardComponentService defaultDatabaseStandardComponentService;

    @Autowired
    public DefaultDatabaseStandardComponentController(DefaultDatabaseStandardComponentService defaultDatabaseStandardComponentService) {
        this.defaultDatabaseStandardComponentService = defaultDatabaseStandardComponentService;
    }


    /**
     * Creates default dataset for components in the database.
     */
    @PostMapping("/create/standard_component/default/dataset")
    public ResponseEntity<Boolean> createComponentDefaultDataSet() {
        List<StandardComponent> accountEntityOpt = defaultDatabaseStandardComponentService.createDefaultDataSet();
        if(!accountEntityOpt.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all component data from the database.
     */
    @DeleteMapping("/delete/standard_component/all")
    public ResponseEntity<Boolean> deleteComponentAllData() {
        defaultDatabaseStandardComponentService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for accounts from the database.
     */
    @DeleteMapping("/delete/standard_component/default/dataset")
    public ResponseEntity<Boolean> deleteComponentDefaultDataset() {
        defaultDatabaseStandardComponentService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
