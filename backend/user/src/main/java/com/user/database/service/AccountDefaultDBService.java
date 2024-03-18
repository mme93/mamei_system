package com.user.database.service;

import com.user.account.model.entity.AccountEntity;
import com.user.account.model.entity.SecurityUserEntity;
import com.user.account.model.entity_enum.Role;
import com.user.account.model.entity_enum.ServicesPrivileges;
import com.user.account.repository.AccountRepository;
import com.user.account.repository.SecurityUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

/**
 * Service class for managing default database operations related to user accounts.
 */
@Service
public class AccountDefaultDBService {

    private final AccountRepository accountRepository;
    private final SecurityUserRepository securityUserRepository;

    @Autowired
    public AccountDefaultDBService(AccountRepository accountRepository, SecurityUserRepository securityUserRepository) {
        this.accountRepository = accountRepository;
        this.securityUserRepository = securityUserRepository;
    }

    /**
     * Creates a default dataset for user accounts based on existing security users.
     */
    public void createDefaultDataSet() {
        List<SecurityUserEntity> securityUserEntityList = securityUserRepository.findAll();
        for (SecurityUserEntity securityUserEntity : securityUserEntityList) {
            if (securityUserEntity.getUsername().equals("admin")) {
                createAccount(securityUserEntity, Role.ADMIN);
            } else if (securityUserEntity.getUsername().equals("superAdmin")) {
                createAccount(securityUserEntity, Role.ADMIN);
            } else if (securityUserEntity.getUsername().equals("user")) {
                createAccount(securityUserEntity, Role.USER);
            } else if (securityUserEntity.getUsername().equals("guest")) {
                createAccount(securityUserEntity, Role.GUEST);
            } else if (securityUserEntity.getUsername().equals("root")) {
                createAccount(securityUserEntity, Role.SYSTEM);
            }
        }

    }

    /**
     * Creates an account for the given security user with the specified role.
     *
     * @param securityUserEntity The security user entity for which the account is to be created.
     * @param role               The role to assign to the account.
     */
    private void createAccount(SecurityUserEntity securityUserEntity, Role role) {
        if (!accountRepository.existsByUserId(Long.valueOf(securityUserEntity.getId()))) {
            String userName = securityUserEntity.getUsername();
            accountRepository.save(new AccountEntity(
                    Long.valueOf(securityUserEntity.getId()),
                    userName,
                    userName,
                    userName,
                    "0201/2147899",
                    getEmail(securityUserEntity.getUsername()),
                    role,
                    generateStringFromList(getMicroServicesPrivileges(userName))
            ));
        }
    }

    /**
     * Generates a comma-separated string representation of a list.
     *
     * @param list The list to generate the string from.
     * @return A string representation of the list, with elements separated by commas.
     */
    private String generateStringFromList(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Retrieves the microservices privileges for the given user.
     *
     * @param userName The username for which privileges are to be retrieved.
     * @return A list of microservices privileges for the user.
     */
    private List<String> getMicroServicesPrivileges(String userName) {
        if (userName.equals("admin")) {
            return asList(ServicesPrivileges.ALL.name());
        } else if (userName.equals("superAdmin")) {
            return asList(ServicesPrivileges.ALL.name());
        } else if (userName.equals("user")) {
            return asList(ServicesPrivileges.ALL.name());
        } else if (userName.equals("guest")) {
            return asList(ServicesPrivileges.ALL.name());
        } else if (userName.equals("root")) {
            return asList(ServicesPrivileges.ALL.name());
        }
        return asList();
    }

    /**
     * Generates an email address based on the username.
     *
     * @param username The username to generate the email address from.
     * @return The generated email address.
     */
    private String getEmail(String username) {
        return username + "@test-mail.de";
    }

    /**
     * Deletes all account data from the default database.
     */
    public void deleteAllData() {
        accountRepository.deleteAll();
    }

    /**
     * Deletes the default dataset for user accounts from the default database.
     */
    public void deleteDefaultDataset() {
        List<SecurityUserEntity> securityUserEntityList = securityUserRepository.findAll();
        for (SecurityUserEntity securityUserEntity : securityUserEntityList) {
            if (securityUserEntity.getUsername().equals("admin")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("superAdmin")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("user")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("guest")) {
                removeAccount(securityUserEntity.getId());
            } else if (securityUserEntity.getUsername().equals("root")) {
                removeAccount(securityUserEntity.getId());
            }
        }
    }

    /**
     * Removes the account associated with the given user ID.
     *
     * @param id The ID of the user whose account is to be removed.
     */
    private void removeAccount(int id) {
        Optional<AccountEntity> accountOpt = accountRepository.findByUserId(Long.valueOf(id));
        if (accountOpt.isPresent()) {
            accountRepository.delete(accountOpt.get());
        }
    }
}
