package com.user.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.account.model.entity.AccountEntity;
import com.user.account.model.entity_enum.Role;
import com.user.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class provides unit tests for the {@link AccountController} class.
 * It ensures the proper functionality of the REST API endpoints related to account operations.
 */
@SpringBootTest(classes = {AccountController.class})
public class AccountControllerTest {

    @Autowired
    @InjectMocks
    private AccountController accountController;

    @MockBean(name = "accountService")
    private AccountService accountService;

    private MockMvc mockMvc;

    private AccountEntity accountEntity;

    private AccountEntity otherAccountEntity;

    /**
     * Initializes the MockMvc instance for testing and mocks dependencies using Mockito.
     */
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .build();
        MockitoAnnotations.openMocks(this);

        accountEntity = new AccountEntity(
                1L,
                1L,
                "FirstName",
                "LastName",
                "UserName",
                "020873342",
                "test@test-mail.de",
                Role.USER,
                "[ALL]");

        otherAccountEntity = new AccountEntity(
                2L,
                2L,
                "OtherFirstName",
                "OtherLastName",
                "OtherUserName",
                "020873342",
                "othertest@test-mail.de",
                Role.USER,
                "[ALL]");
    }


    /**
     * Tests the retrieval of an account by user ID when the account exists.
     * Expects a successful response with the account details.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccountByUserId() throws Exception {
        Mockito.when(accountService.getAccountByUserId(any())).thenReturn(Optional.of(accountEntity));
        mockMvc.perform(MockMvcRequestBuilders.get("/account/userId/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountEntity.getId()))
                .andExpect(jsonPath("$.userId").value(accountEntity.getUserId()))
                .andExpect(jsonPath("$.firstName").value(accountEntity.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(accountEntity.getLastName()))
                .andExpect(jsonPath("$.username").value(accountEntity.getUsername()))
                .andExpect(jsonPath("$.callNumber").value(accountEntity.getCallNumber()))
                .andExpect(jsonPath("$.email").value(accountEntity.getEmail()))
                .andExpect(jsonPath("$.role").value(accountEntity.getRole().toString()))
                .andExpect(jsonPath("$.microServices").value(accountEntity.getMicroServices()))
                .andReturn();
    }

    /**
     * Tests the retrieval of an account by user ID when the account does not exist.
     * Expects a "Not Found" status response.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccountByUserIdStatusNotFound() throws Exception {
        Mockito.when(accountService.getAccountByUserId(any())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/account/userId/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    /**
     * Tests the retrieval of an account by its ID.
     * Expects a successful response with the account details.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccountById() throws Exception {
        Mockito.when(accountService.getAccountById(any())).thenReturn(Optional.of(accountEntity));
        mockMvc.perform(MockMvcRequestBuilders.get("/account/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountEntity.getId()))
                .andExpect(jsonPath("$.userId").value(accountEntity.getUserId()))
                .andExpect(jsonPath("$.firstName").value(accountEntity.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(accountEntity.getLastName()))
                .andExpect(jsonPath("$.username").value(accountEntity.getUsername()))
                .andExpect(jsonPath("$.callNumber").value(accountEntity.getCallNumber()))
                .andExpect(jsonPath("$.email").value(accountEntity.getEmail()))
                .andExpect(jsonPath("$.role").value(accountEntity.getRole().toString()))
                .andExpect(jsonPath("$.microServices").value(accountEntity.getMicroServices()))
                .andReturn();
    }

    /**
     * Tests the retrieval of an account by its ID when the account does not exist.
     * Expects a "Not Found" status response.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccountByIdStatusNotFound() throws Exception {
        Mockito.when(accountService.getAccountById(any())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/account/id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    /**
     * Tests the retrieval of multiple accounts.
     * Expects a successful response with details of all accounts.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccounts() throws Exception {
        Mockito.when(accountService.getAccounts()).thenReturn(asList(accountEntity, otherAccountEntity));
        mockMvc.perform(MockMvcRequestBuilders.get("/account/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(accountEntity.getId()))
                .andExpect(jsonPath("$.[0].userId").value(accountEntity.getUserId()))
                .andExpect(jsonPath("$.[0].firstName").value(accountEntity.getFirstName()))
                .andExpect(jsonPath("$.[0].lastName").value(accountEntity.getLastName()))
                .andExpect(jsonPath("$.[0].username").value(accountEntity.getUsername()))
                .andExpect(jsonPath("$.[0].callNumber").value(accountEntity.getCallNumber()))
                .andExpect(jsonPath("$.[0].email").value(accountEntity.getEmail()))
                .andExpect(jsonPath("$.[0].role").value(accountEntity.getRole().toString()))
                .andExpect(jsonPath("$.[0].microServices").value(accountEntity.getMicroServices()))
                .andExpect(jsonPath("$.[1].id").value(otherAccountEntity.getId()))
                .andExpect(jsonPath("$.[1].userId").value(otherAccountEntity.getUserId()))
                .andExpect(jsonPath("$.[1].firstName").value(otherAccountEntity.getFirstName()))
                .andExpect(jsonPath("$.[1].lastName").value(otherAccountEntity.getLastName()))
                .andExpect(jsonPath("$.[1].username").value(otherAccountEntity.getUsername()))
                .andExpect(jsonPath("$.[1].callNumber").value(otherAccountEntity.getCallNumber()))
                .andExpect(jsonPath("$.[1].email").value(otherAccountEntity.getEmail()))
                .andExpect(jsonPath("$.[1].role").value(otherAccountEntity.getRole().toString()))
                .andExpect(jsonPath("$.[1].microServices").value(otherAccountEntity.getMicroServices()))
                .andReturn();
    }

    /**
     * Tests the retrieval of multiple accounts when no accounts are available.
     * Expects a "Not Found" status response.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testGetAccountsNotFound() throws Exception {
        Mockito.when(accountService.getAccounts()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/account/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    /**
     * Tests the update of an existing account.
     * Expects a successful response after the update operation.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testUpdateAccount() throws Exception {
        String json = new ObjectMapper().writeValueAsString(accountEntity);
        mockMvc.perform(MockMvcRequestBuilders.put("/account/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Mockito.verify(accountService).updateAccount(any());
    }


    /**
     * Tests the creation of a new account.
     * Expects a successful response after the account creation.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testNewAccount() throws Exception {
        String json = new ObjectMapper().writeValueAsString(accountEntity);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Mockito.verify(accountService).createAccount(any());
    }

    /**
     * Tests the deletion of an account by its ID.
     * Expects a successful deletion operation.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testDeleteAccountById() throws Exception {
        Mockito.when(accountService.deleteAccountById(1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/id/{id}", 1L))
                .andExpect(status().isOk());
    }

    /**
     * Tests the scenario where no account is found for deletion by its ID.
     * Expects a "Not Found" status response.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testNoUserFoundByIdDeleteAccount() throws Exception {
        Long invalidId = 999L;
        Mockito.when(accountService.deleteAccountById(invalidId)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/id/{id}", invalidId))
                .andExpect(status().isNotFound());
    }

    /**
     * Tests the deletion of an account by its user ID.
     * Expects a successful deletion operation.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testDeleteAccountByUserId() throws Exception {
        Mockito.when(accountService.deleteAccountByUserId(1L)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/userId/{id}", 1L))
                .andExpect(status().isOk());
    }

    /**
     * Tests the scenario where no account is found for deletion by its user ID.
     * Expects a "Not Found" status response.
     *
     * @throws Exception if an error occurs during the test execution
     */
    @Test
    void testNoUserFoundByUserIdDeleteAccount() throws Exception {
        Long invalidId = 999L;
        Mockito.when(accountService.deleteAccountByUserId(invalidId)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/account/delete/userId/{id}", invalidId))
                .andExpect(status().isNotFound());
    }
}
