package com.mameie_fsm.database.controller;

import com.mameie_fsm.database.service.DefaultDatabaseSchemeService;
import com.mameie_fsm.gui.components.standard.model.StandardComponent;
import com.mameie_fsm.gui.scheme.model.Scheme;
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
     * Creates default dataset for components in the database.
     */
    @PostMapping("/create/scheme/default/dataset")
    public ResponseEntity<Boolean> createSchemeDefaultDataSet() {
        List<Scheme> schemeList = defaultDatabaseSchemeService.createDefaultDataSet();
        if(!schemeList.isEmpty()){
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
