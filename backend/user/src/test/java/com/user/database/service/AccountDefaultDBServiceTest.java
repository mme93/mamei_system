package com.user.database.service;


import com.user.account.model.entity.AccountEntity;
import com.user.account.model.entity.SecurityUserEntity;
import com.user.account.model.entity_enum.Role;
import com.user.account.model.entity_enum.UserCollection;
import com.user.account.repository.AccountRepository;
import com.user.account.repository.SecurityUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Unit tests for the {@link AccountDefaultDBService} class.
 */
@SpringBootTest(classes = {AccountDefaultDBService.class})
public class AccountDefaultDBServiceTest {

    @Autowired
    @InjectMocks
    private AccountDefaultDBService accountDefaultDBService;

    @MockBean(name = "accountRepository")
    private AccountRepository accountRepository;

    @MockBean(name = "securityUserRepository")
    private SecurityUserRepository securityUserRepository;

    private AccountEntity accountEntity;

    private SecurityUserEntity securityUserEntity;

    /**
     * Initializes mock objects before each test method.
     */
    @BeforeEach
    public void init() {
        openMocks(this);
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
        securityUserEntity = new SecurityUserEntity(
                1,
                "admin",
                "password",
                UserCollection.USER
        );
    }

    /**
     * Test method to verify the creation of default data set.
     */
    @Test
    void testCreateDefaultDataSet() {
        for (String userName : asList("admin", "superAdmin", "user", "guest", "root")) {
            securityUserEntity.setUsername(userName);
            accountEntity.setUsername(userName);
            when(securityUserRepository.findAll()).thenReturn(asList(securityUserEntity));
            when(accountRepository.existsByUserId(any())).thenReturn(false);
            when(accountRepository.save(any())).thenReturn(accountEntity);
            Optional<AccountEntity> resultOpt = accountDefaultDBService.createDefaultDataSet();
            assertTrue(resultOpt.isPresent());
            AccountEntity result = resultOpt.get();
            assertEquals(result.getUserId(), Long.valueOf(securityUserEntity.getId()));
            assertEquals(result.getRole().toString(), securityUserEntity.getUserCollection().toString());
            assertEquals(result.getUsername(), userName);
            assertEquals(result.getEmail(), userName + "@test-mail.de");
        }
    }

    /**
     * Test method to verify no creation of default data set by wrong user name.
     */
    @Test
    void testNoCreateDefaultDataSetByWrongUserName() {
        securityUserEntity.setUsername("test");
        when(securityUserRepository.findAll()).thenReturn(asList(securityUserEntity));
        when(accountRepository.existsByUserId(any())).thenReturn(false);
        when(accountRepository.save(any())).thenReturn(accountEntity);
        Optional<AccountEntity> resultOpt = accountDefaultDBService.createDefaultDataSet();
        assertFalse(resultOpt.isPresent());
    }

    /**
     * Test method to verify retrieval of no microservices privileges by wrong user name.
     */
    @Test
    void testGetNoMicroServicesPrivilegesByWrongUserName(){
        List<String> resultList=accountDefaultDBService.getMicroServicesPrivileges("test");
        assertEquals(resultList.size(),0);
    }


}
