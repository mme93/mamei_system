package com.user.account.service;

import com.user.account.model.entity.AccountEntity;
import com.user.account.model.entity_enum.Role;
import com.user.account.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit tests for the {@link AccountService} class.
 */
@SpringBootTest(classes = {AccountService.class})
public class AccountServiceTest {

    @Autowired
    @InjectMocks
    private AccountService accountService;

    @MockBean(name = "accountRepository")
    private AccountRepository accountRepository;

    private AccountEntity accountEntity;

    /**
     * Initializes mock objects before each test method.
     */
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        accountEntity = new AccountEntity(
                1L,
                "FirstName",
                "LastName",
                "UserName",
                "020873342",
                "test@test-mail.de",
                Role.USER,
                "[ALL]"
        );
    }

    /**
     * Tests the getAccountByUserId method when an account is found.
     */
    @Test
    void testFindAccountByUserId() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.of(accountEntity));
        Optional<AccountEntity> resultAccountOpt = accountService.getAccountByUserId(1L);

        Assertions.assertTrue(resultAccountOpt.isPresent());

        AccountEntity resultAccount = resultAccountOpt.get();
        Assertions.assertEquals(resultAccount.getUserId(), accountEntity.getUserId());
        Assertions.assertEquals(resultAccount.getFirstName(), accountEntity.getFirstName());
        Assertions.assertEquals(resultAccount.getLastName(), accountEntity.getLastName());
        Assertions.assertEquals(resultAccount.getUsername(), accountEntity.getUsername());
        Assertions.assertEquals(resultAccount.getCallNumber(), accountEntity.getCallNumber());
        Assertions.assertEquals(resultAccount.getEmail(), accountEntity.getEmail());
        Assertions.assertEquals(resultAccount.getRole(), accountEntity.getRole());
        Assertions.assertEquals(resultAccount.getMicroServices(), accountEntity.getMicroServices());

    }

    /**
     * Tests the getAccountByUserId method when no account is found for the given user ID.
     */
    @Test
    void testFindNotAccountByUserId() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.empty());
        Optional<AccountEntity> resultAccountOpt = accountService.getAccountByUserId(1L);
        Assertions.assertFalse(resultAccountOpt.isPresent());
    }

    /**
     * Tests the getAccountById method when an account is found.
     */
    @Test
    void testFindAccountById() {
        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));
        Optional<AccountEntity> resultAccountOpt = accountService.getAccountById(1L);

        Assertions.assertTrue(resultAccountOpt.isPresent());

        AccountEntity resultAccount = resultAccountOpt.get();
        Assertions.assertEquals(resultAccount.getUserId(), accountEntity.getUserId());
        Assertions.assertEquals(resultAccount.getFirstName(), accountEntity.getFirstName());
        Assertions.assertEquals(resultAccount.getLastName(), accountEntity.getLastName());
        Assertions.assertEquals(resultAccount.getUsername(), accountEntity.getUsername());
        Assertions.assertEquals(resultAccount.getCallNumber(), accountEntity.getCallNumber());
        Assertions.assertEquals(resultAccount.getEmail(), accountEntity.getEmail());
        Assertions.assertEquals(resultAccount.getRole(), accountEntity.getRole());
        Assertions.assertEquals(resultAccount.getMicroServices(), accountEntity.getMicroServices());

    }

    /**
     * Tests the getAccountById method when no account is found for the given ID.
     */
    @Test
    void testFindNotAccountById() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.empty());
        Optional<AccountEntity> resultAccountOpt = accountService.getAccountById(1L);
        Assertions.assertFalse(resultAccountOpt.isPresent());
    }

    /**
     * Tests the getAccounts method when accounts are found.
     */
    @Test
    void testFindAllAccounts() {
        Mockito.when(accountRepository.findAll()).thenReturn(asList(accountEntity));
        List<AccountEntity> resultList = accountService.getAccounts();
        Assertions.assertEquals(resultList.size(), 1);
    }

    /**
     * Tests the getAccounts method when no accounts are found.
     */
    @Test
    void testFindNoAccounts() {
        Mockito.when(accountRepository.findAll()).thenReturn(Collections.emptyList());
        List<AccountEntity> resultList = accountService.getAccounts();
        Assertions.assertEquals(resultList.size(), 0);
    }

    /**
     * Tests the updateAccount method when an account is successfully updated.
     */
    @Test
    void testUpdateAccount() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.of(accountEntity));
        Assertions.assertTrue(accountService.updateAccount(accountEntity));
    }

    /**
     * Tests the updateAccount method when no account is found to update.
     */
    @Test
    void testNoAccountFoundToUpdate() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.empty());
        Assertions.assertFalse(accountService.updateAccount(accountEntity));
    }

    /**
     * Tests the createAccount method to verify if an account is created successfully.
     */
    @Test
    void testCreateAccount() {
        accountService.createAccount(accountEntity);
        Mockito.verify(accountRepository).save(any());
    }

    /**
     * Tests the deleteAccountById method when an account is successfully deleted.
     */
    @Test
    void testDeleteAccountById() {
        Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(accountEntity));
        boolean isAccountDeleted = accountService.deleteAccountById(1L);
        Assertions.assertTrue(isAccountDeleted);
        Mockito.verify(accountRepository).delete(any());
    }

    /**
     * Tests the deleteAccountByUserId method when an account is successfully deleted.
     */
    @Test
    void testDeleteAccountByUserId() {
        Mockito.when(accountRepository.findByUserId(any())).thenReturn(Optional.of(accountEntity));
        boolean isAccountDeleted = accountService.deleteAccountByUserId(1L);
        Assertions.assertTrue(isAccountDeleted);
        Mockito.verify(accountRepository).delete(any());
    }

}
