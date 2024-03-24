package com.user.account.service;

import com.user.account.model.entity.AccountEntity;
import com.user.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing account-related operations.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Retrieves an account by user ID.
     *
     * @param id The ID of the user
     * @return Optional<AccountEntity> The optional containing the account entity if found
     */
    public Optional<AccountEntity> getAccountByUserId(Long id) {
        return accountRepository.findByUserId(id);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id The ID of the account
     * @return Optional<AccountEntity> The optional containing the account entity if found
     */
    public Optional<AccountEntity> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * Retrieves all accounts.
     *
     * @return List<AccountEntity> The list of account entities
     */
    public List<AccountEntity> getAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Updates an existing account.
     *
     * @param accountEntity The account entity to be updated
     * @return boolean True if the account is updated successfully, otherwise false
     */
    public boolean updateAccount(AccountEntity accountEntity) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByUserId(accountEntity.getUserId());
        if (optionalAccount.isPresent()) {
            AccountEntity resultAccount = optionalAccount.get();
            resultAccount.setFirstName(accountEntity.getFirstName());
            resultAccount.setLastName(accountEntity.getLastName());
            resultAccount.setUsername(accountEntity.getUsername());
            resultAccount.setCallNumber(accountEntity.getCallNumber());
            resultAccount.setEmail(accountEntity.getEmail());
            resultAccount.setRole(accountEntity.getRole());
            resultAccount.setMicroServices(accountEntity.getMicroServices());
            accountRepository.save(resultAccount);
            return true;
        }
        return false;
    }

    /**
     * Creates a new account.
     *
     * @param accountEntity The account entity to be created
     */
    public void createAccount(AccountEntity accountEntity) {
        accountRepository.save(accountEntity);
    }

    /**
     * Deletes an account by user ID.
     *
     * @param userId The ID of the user whose account is to be deleted
     * @return boolean True if the account is deleted successfully, otherwise false
     */
    public boolean deleteAccountByUserId(Long userId) {
        Optional<AccountEntity> optionalAccount = accountRepository.findByUserId(userId);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return true;
        }
        return false;
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id The ID of the account to be deleted
     * @return boolean True if the account is deleted successfully, otherwise false
     */
    public boolean deleteAccountById(Long id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.delete(optionalAccount.get());
            return true;
        }
        return false;
    }
}
