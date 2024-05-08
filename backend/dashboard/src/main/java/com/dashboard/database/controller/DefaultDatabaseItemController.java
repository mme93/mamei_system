package com.dashboard.database.controller;


import com.dashboard.database.service.DefaultDatabaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/default_database/item")
public class DefaultDatabaseItemController {

    private final DefaultDatabaseItemService defaultDatabaseItemService;

    @Autowired
    public DefaultDatabaseItemController(DefaultDatabaseItemService defaultDatabaseItemService) {
        this.defaultDatabaseItemService = defaultDatabaseItemService;
    }

    /**
     * Creates default dataset for item´s in the database.
     */
    @PostMapping("/create/item/default/dataset")
    public ResponseEntity<Boolean> createComponentDefaultDataSet() {
        List<Object> accountEntityOpt = defaultDatabaseItemService.createDefaultDataSet();
        if(!accountEntityOpt.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all item´s data from the database.
     */
    @DeleteMapping("/delete/item/all")
    public ResponseEntity<Boolean> deleteComponentAllData() {
        defaultDatabaseItemService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for item´s from the database.
     */
    @DeleteMapping("/delete/item/default/dataset")
    public ResponseEntity<Boolean> deleteComponentDefaultDataset() {
        defaultDatabaseItemService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
