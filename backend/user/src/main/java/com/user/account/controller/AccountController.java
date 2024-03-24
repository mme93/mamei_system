package com.user.account.controller;

import com.user.account.model.entity.AccountEntity;
import com.user.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * Controller for managing accounts.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Retrieves an account by user ID.
     *
     * @param id The ID of the user
     * @return ResponseEntity<AccountEntity> The ResponseEntity containing the account entity if found, otherwise NOT_FOUND status
     */
    @GetMapping("/userId/{id}")
    public ResponseEntity<AccountEntity> getAccountByUserId(@PathVariable Long id) {
        Optional<AccountEntity> accountEntity = accountService.getAccountByUserId(id);
        if (accountEntity.isPresent()) {
            return new ResponseEntity<>(accountEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id The ID of the account
     * @return ResponseEntity<AccountEntity> The ResponseEntity containing the account entity if found, otherwise NOT_FOUND status
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Long id) {
        Optional<AccountEntity> accountEntity = accountService.getAccountById(id);
        if (accountEntity.isPresent()) {
            return new ResponseEntity<>(accountEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Retrieves all accounts.
     *
     * @return ResponseEntity<List<AccountEntity>> The ResponseEntity containing a list of account entities if found, otherwise NOT_FOUND status
     */
    @GetMapping("/all")
    public ResponseEntity<List<AccountEntity>> getAccounts() {
        List<AccountEntity> accountEntityList = accountService.getAccounts();
        if (!accountEntityList.isEmpty()) {
            return new ResponseEntity<>(accountEntityList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Updates an existing account.
     *
     * @param accountEntity The account entity to be updated
     * @return ResponseEntity<?> The ResponseEntity with OK status
     */
    @PutMapping("/update")
    public ResponseEntity updateAccount(@RequestBody AccountEntity accountEntity) {
        accountService.updateAccount(accountEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Creates a new account.
     *
     * @param accountEntity The account entity to be created
     * @return ResponseEntity<?> The ResponseEntity with OK status
     */
    @PostMapping("/new")
    public ResponseEntity createAccount(@RequestBody AccountEntity accountEntity) {
        accountService.createAccount(accountEntity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Deletes an account by user ID.
     *
     * @param id The ID of the user whose account is to be deleted
     * @return ResponseEntity<?> The ResponseEntity with OK status if deletion is successful, otherwise NOT_FOUND status
     */
    @DeleteMapping("/delete/userId/{id}")
    public ResponseEntity deleteAccountByUserId(@PathVariable Long id) {

        if (accountService.deleteAccountByUserId(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Deletes an account by its ID.
     *
     * @param id The ID of the account to be deleted
     * @return ResponseEntity<?> The ResponseEntity with OK status if deletion is successful, otherwise NOT_FOUND status
     */
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity deleteAccountById(@PathVariable Long id) {

        if (accountService.deleteAccountById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
