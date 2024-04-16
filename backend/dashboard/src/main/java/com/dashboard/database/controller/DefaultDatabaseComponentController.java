package com.dashboard.database.controller;

import com.dashboard.activity.gui.components.model.ComponentEntity;
import com.dashboard.database.service.DefaultDatabaseComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/default_database/component")
public class DefaultDatabaseComponentController {

    private final DefaultDatabaseComponentService defaultDatabaseComponentService;

    @Autowired
    public DefaultDatabaseComponentController(DefaultDatabaseComponentService defaultDatabaseComponentService) {
        this.defaultDatabaseComponentService = defaultDatabaseComponentService;
    }

    /**
     * Creates default dataset for components in the database.
     */
    @PostMapping("/create/component/default/dataset")
    public ResponseEntity<Boolean> createComponentDefaultDataSet() {
        List<ComponentEntity> accountEntityOpt = defaultDatabaseComponentService.createDefaultDataSet();
        if(!accountEntityOpt.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all component data from the database.
     */
    @DeleteMapping("/delete/component/all")
    public ResponseEntity<Boolean> deleteComponentAllData() {
        defaultDatabaseComponentService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for accounts from the database.
     */
    @DeleteMapping("/delete/component/default/dataset")
    public ResponseEntity<Boolean> deleteComponentDefaultDataset() {
        defaultDatabaseComponentService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
