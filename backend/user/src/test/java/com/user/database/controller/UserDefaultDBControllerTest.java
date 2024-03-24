package com.user.database.controller;

import com.user.account.model.entity.AccountEntity;
import com.user.database.service.AccountDefaultDBService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains unit tests for the functionalities of the UserDefaultDBController class.
 */
@SpringBootTest(classes = {UserDefaultDBController.class})
public class UserDefaultDBControllerTest {

    @Autowired
    @InjectMocks
    private UserDefaultDBController userDefaultDBController;

    @MockBean(name = "accountDefaultDBService")
    private AccountDefaultDBService accountDefaultDBService;

    private MockMvc mockMvc;

    /**
     * Initializes the MockMvc instance for testing and mocks dependencies using Mockito.
     */
    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userDefaultDBController)
                .build();
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to verify the behavior of creating an account default data set when the operation results in a conflict.
     *
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    void testCreateAccountDefaultDataSetStatusConflict() throws Exception {
        when(accountDefaultDBService.createDefaultDataSet()).thenReturn(emptyList());
        mockMvc.perform(MockMvcRequestBuilders.post("/defaultDB/create/account/default/dataset")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Test case to verify the behavior of creating an account default data set when the operation is successful.
     *
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    void testCreateAccountDefaultDataSetStatusCreated() throws Exception {
        when(accountDefaultDBService.createDefaultDataSet()).thenReturn(asList(new AccountEntity()));
        mockMvc.perform(MockMvcRequestBuilders.post("/defaultDB/create/account/default/dataset")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * Test case to verify the behavior of deleting all account data when the operation is successful.
     *
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    void testDeleteAccountAllDataStatusOk() throws Exception {
        when(accountDefaultDBService.createDefaultDataSet()).thenReturn(asList(new AccountEntity()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/defaultDB/delete/account/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    /**
     * Test case to verify the behavior of deleting the account default data set when the operation is successful.
     *
     * @throws Exception if any error occurs during the test execution
     */
    @Test
    void testDeleteAccountDefaultDatasetStatusOk() throws Exception {
        when(accountDefaultDBService.createDefaultDataSet()).thenReturn(asList(new AccountEntity()));
        mockMvc.perform(MockMvcRequestBuilders.delete("/defaultDB/delete/account/default/dataset")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
