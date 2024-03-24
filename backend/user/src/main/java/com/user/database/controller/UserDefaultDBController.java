package com.user.database.controller;

import com.user.account.model.entity.AccountEntity;
import com.user.database.service.AccountDefaultDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller class for managing operations related to default database entities.
 */
@RestController
@RequestMapping("/defaultDB")
public class UserDefaultDBController {

    private final AccountDefaultDBService accountDefaultDBService;

    @Autowired
    public UserDefaultDBController(AccountDefaultDBService accountDefaultDBService) {
        this.accountDefaultDBService = accountDefaultDBService;
    }

    /**
     * Creates default dataset for accounts in the database.
     */
    @PostMapping("/create/account/default/dataset")
    public ResponseEntity<Boolean> createAccountDefaultDataSet() {
        Optional<AccountEntity> accountEntityOpt = accountDefaultDBService.createDefaultDataSet();
        if(accountEntityOpt.isPresent()){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(true, HttpStatus.CONFLICT);
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
