package com.user.controller;

import com.user.service.defaultdb.AccountDefaultDBService;
import com.user.service.defaultdb.PrivilegesDefaultDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing operations related to default database entities.
 */
@RestController
@RequestMapping("/defaultDB")
public class UserDefaultDBController {

    private final PrivilegesDefaultDBService privilegesDefaultDBService;
    private final AccountDefaultDBService accountDefaultDBService;

    @Autowired
    public UserDefaultDBController(PrivilegesDefaultDBService privilegesDefaultDBService, AccountDefaultDBService accountDefaultDBService) {
        this.privilegesDefaultDBService = privilegesDefaultDBService;
        this.accountDefaultDBService = accountDefaultDBService;
    }


    /**
     * Creates default dataset for privileges in the default database.
     */
    @PostMapping("/create/privileges/default/dataset")
    public void createPrivilegesDefaultDataSet() {
        privilegesDefaultDBService.createDefaultDataSet();
    }

    /**
     * Deletes all privileges data from the database.
     */
    @DeleteMapping("/delete/privileges/all")
    public void deletePrivilegesAllData() {
        privilegesDefaultDBService.deleteAllData();
    }

    /**
     * Deletes the default dataset for privileges from the database.
     */
    @DeleteMapping("/delete/privileges/default/dataset")
    public void deletePrivilegesDefaultDataset() {
        privilegesDefaultDBService.deleteDefaultDataset();
    }

    /**
     * Creates default dataset for accounts in the database.
     */
    @PostMapping("/create/account/default/dataset")
    public ResponseEntity<Boolean> createAccountDefaultDataSet() {
        accountDefaultDBService.createDefaultDataSet();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes all account data from the database.
     */
    @DeleteMapping("/delete/account/all")
    public ResponseEntity<Boolean> deleteAccountAllData() {
        accountDefaultDBService.deleteAllData();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    /**
     * Deletes the default dataset for accounts from the database.
     */
    @DeleteMapping("/delete/account/default/dataset")
    public ResponseEntity<Boolean> deleteAccountDefaultDataset() {
        accountDefaultDBService.deleteDefaultDataset();
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
