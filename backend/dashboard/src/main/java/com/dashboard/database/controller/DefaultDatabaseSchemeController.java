package com.dashboard.database.controller;

import com.dashboard.activity.gui.components.model.ComponentEntity;
import com.dashboard.activity.gui.scheme.model.SchemeEntity;
import com.dashboard.database.service.DefaultDatabaseSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/default_database/scheme")
public class DefaultDatabaseSchemeController {

    private final DefaultDatabaseSchemeService defaultDatabaseSchemeService;

    @Autowired
    public DefaultDatabaseSchemeController(DefaultDatabaseSchemeService defaultDatabaseSchemeService) {
        this.defaultDatabaseSchemeService = defaultDatabaseSchemeService;
    }

    /**
     * Creates default dataset for accounts in the database.
     */
    @PostMapping("/create/scheme/default/dataset")
    public ResponseEntity<Boolean> createSchemeDefaultDataSet() {
        List<SchemeEntity> schemeEntityOpt = defaultDatabaseSchemeService.createDefaultDataSet();
        if(!schemeEntityOpt.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all component data from the database.
     */
    @DeleteMapping("/delete/scheme/all")
    public ResponseEntity<Boolean> deleteSchemeAllData() {
        defaultDatabaseSchemeService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for accounts from the database.
     */
    @DeleteMapping("/delete/scheme/default/dataset")
    public ResponseEntity<Boolean> deleteSchemeDefaultDataset() {
        defaultDatabaseSchemeService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
