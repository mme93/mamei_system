package com.dashboard.database.controller;

import com.dashboard.activity.items.model.value.typ.ItemValueTyp;
import com.dashboard.database.service.DefaultDatabaseItemValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/default_database/value_item")
public class DefaultDatabaseItemValueController {

    private final DefaultDatabaseItemValueService defaultDatabaseItemValueService;

    @Autowired
    public DefaultDatabaseItemValueController(DefaultDatabaseItemValueService defaultDatabaseItemValueService) {
        this.defaultDatabaseItemValueService = defaultDatabaseItemValueService;
    }

    /**
     * Creates default dataset for item values in the database.
     */
    @PostMapping("/create/value_item/default/dataset")
    public ResponseEntity<Boolean> createItemValueDefaultDataSet() {
        List<ItemValueTyp> accountEntityOpt = defaultDatabaseItemValueService.createDefaultDataSet();
        if(!accountEntityOpt.isEmpty()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all item values data from the database.
     */
    @DeleteMapping("/delete/value_item/all")
    public ResponseEntity<Boolean> deleteItemValueAllData() {
        defaultDatabaseItemValueService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for item values from the database.
     */
    @DeleteMapping("/delete/value_item/default/dataset")
    public ResponseEntity<Boolean> deleteItemValueDefaultDataset() {
        defaultDatabaseItemValueService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
